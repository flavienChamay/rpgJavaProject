package bataille;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import attaque.Pouvoir;
import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;
import protagoniste.ZoneDeCombatNonCompatibleException;

public class Grotte {
	private LinkedHashMap<Salle, List<Salle>> planGrotte = new LinkedHashMap<>();
	private HashMap<Salle, Bataille> batailles = new HashMap<>();
	private HashSet<Salle> sallesExplorees = new HashSet<>();
	private int numeroSalleDecisive;

	/**
	 * Create a Salle with number the size of planGrotte and the ZoneDeCombat
	 * @param zoneDeCombat
	 * @param monstres
	 */
	public void ajouterSalle(ZoneDeCombat zoneDeCombat, Monstre<? extends Pouvoir>... monstres) throws ZoneDeCombatNonCompatibleException {
		Salle salleAjoutee = new Salle(this.planGrotte.size() + 1, zoneDeCombat);
		Bataille batailleAjout = new Bataille();
		for(Monstre<? extends Pouvoir> mons : monstres) {
			if(mons.getZoneDeCombat() != zoneDeCombat) {
				throw new ZoneDeCombatNonCompatibleException("La zone de combat de la salle est de type " 
			+ zoneDeCombat + " alors que celle du montre est " + mons.getZoneDeCombat());
			}
			batailleAjout.ajouter(mons);
		}
		planGrotte.put(salleAjoutee, new ArrayList<Salle>());
		batailles.put(salleAjoutee, batailleAjout);
	}

	/**
	 * Display the map of the grotte
	 * @return
	 */
	public String afficherPlanGrotte() {
		StringBuilder affichage = new StringBuilder();
		for (Map.Entry<Salle, List<Salle>> entry : planGrotte.entrySet()) {
			Salle salle = entry.getKey();
			List<Salle> acces = planGrotte.get(salle);
			affichage.append("La " + salle + ".\nelle possede " + acces.size() + " acces : ");
			for (Salle access : acces) {
				affichage.append(" vers la salle " + access);
			}
			Bataille bataille = batailles.get(salle);
			Camp<Monstre<? extends Pouvoir>> camp = bataille.getCampMonstres();
			Monstre<? extends Pouvoir> monstre = camp.selectionner();
			if (camp.nbCombattants() > 1) {
				affichage.append("\n" + camp.nbCombattants() + " monstres de type ");
			} else {
				affichage.append("\nUn monstre de type ");
			}
			affichage.append(monstre.getNom() + " la protege.\n");
			if (salle.getNumSalle() == numeroSalleDecisive) {
				affichage.append("C'est dans cette salle que se trouve la pierre de sang.\n");
			}
			affichage.append("\n");
		}
		return affichage.toString();
	}
	
	/**
	 * Find the Salle with its number
	 * @param numeroSalle
	 * @return
	 */
	private Salle trouverSalle(int numeroSalle) {
		int indice = 0;
		for(Map.Entry<Salle, List<Salle>> entry : planGrotte.entrySet()) {
			indice++;
			if(indice == numeroSalle) {
				return entry.getKey();
			}
		}
		return new Salle(0, null);
	}
	
	/**
	 * Find the original Salle and fin the set of accessible Salles
	 * @param salleOriginale
	 * @param acces
	 */
	public void configurerAcces(int salleOriginale, int... acces) {
		Salle s = trouverSalle(salleOriginale);
		List<Salle> listeDeSalle = planGrotte.get(s);
		for(int indiceAcces : acces) {
			listeDeSalle.add(trouverSalle(indiceAcces));
		}
	}

	/**
	 * Setter of the numero Salle decisive
	 * @param numeroSalleDecisive
	 */
	public void setNumeroSalleDecisive(int numeroSalleDecisive) {
		this.numeroSalleDecisive = numeroSalleDecisive;
	}
	
	/**
	 * A partir d'une salle passee en parametre retourne un booleen permettant de savoir
	 * si elle contient la pierre de sang ou non
	 * @param salle
	 * @return
	 */
	public boolean salleDecisive(Salle salle) {
		return salle.getNumSalle() == numeroSalleDecisive;
	}
	
	/**
	 * Retourne la premiere salle de la grotte et l'ajoute aux salles explorees
	 * @return
	 */
	public Salle premiereSalle() {
		Salle salleUn = trouverSalle(1);
		sallesExplorees.add(salleUn);
		return salleUn;
	}
	
	/**
	 * Retourne la prochaine salle a explorer au hasard
	 * @param salleSource
	 * @return
	 */
	public Salle salleSuivante(Salle salleSource) {
		List<Salle> listeSalles = planGrotte.get(salleSource);
		listeSalles.removeAll(sallesExplorees);
		if(listeSalles.isEmpty()) {
			sallesExplorees.clear();
			listeSalles = planGrotte.get(salleSource);
		}
		Random randGen = new Random();
		return listeSalles.get(randGen.nextInt(listeSalles.size()));
	}

	/**
	 * Getter of the plant of the grotte
	 * @return
	 */
	public LinkedHashMap<Salle, List<Salle>> getPlanGrotte() {
		return planGrotte;
	}

	/**
	 * Getter of the batailles
	 * @return
	 */
	public HashMap<Salle, Bataille> getBatailles() {
		return batailles;
	}
	
	
	
}

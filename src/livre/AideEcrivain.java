package livre;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NavigableSet;
import java.util.TreeSet;

import attaque.Pouvoir;
import bataille.Bataille;
import protagoniste.Domaine;
import protagoniste.EtreVivant;
import protagoniste.Heros;
import protagoniste.Homme;
import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;

public class AideEcrivain {
	private Bataille bataille;
	private NavigableSet<Monstre<?>> monstresDeFeu;
	private NavigableSet<Monstre<?>> monstresDeGlace;
	private NavigableSet<Monstre<?>> monstresTranchants;
	//attribute who is sorted by domain of a monster, if it is equal then by natural order
	private NavigableSet<Monstre<?>> monstresDomaineSet =
			new TreeSet<>(
					new Comparator<Monstre<?>>() {
						public int compare(Monstre<?> m1, Monstre<?> m2) {
							if(m1.getDomaine() == m2.getDomaine()) {
								return m1.compareTo(m2);
							}
							else {
								return m1.getDomaine().compareTo(m2.getDomaine());
							}
						}
					});
	//attribute who is sorted by zone of a monster, if it is equal then by life point, if it is again equal then by natural order
	private NavigableSet<Monstre<?>> monstresZoneSet = 
			new TreeSet<>(
					new Comparator<Monstre<?>>() {
						public int compare(Monstre<?> m1, Monstre<?> m2) {
							int comp = m1.getZoneDeCombat().compareTo(m2.getZoneDeCombat());
							if(comp == 0) { //if same zone of combat
								comp = m2.getForceDeVie() - m1.getForceDeVie();
								if(comp == 0) { //si meme force de vie
									comp = m1.compareTo(m2); //ordre naturel
								}
							}
							return comp;
						}
					});

	/**
	 * Constructor of the class AideEcrivain
	 * @param bataille
	 */
	public AideEcrivain(Bataille bataille) {
		this.bataille = bataille;
	}

	/**
	 * Returns a sorted list of humans in the human camp, first Heros, after Homme
	 * @return the sorted list of humans in the camp 
	 */
	public LinkedList<EtreVivant> visualiserForcesHumaines() {
		LinkedList<EtreVivant> listeTriee = new LinkedList<>();
		ListIterator<EtreVivant> iterateur = listeTriee.listIterator();
		for (EtreVivant homme : this.bataille.getCampHumains()) {
			if(homme.getClass() == Heros.class) {
				trouverFinDesHeros(iterateur);
			}
			else {
				trouverFinDesHommes(iterateur);
			}
			iterateur.add(homme);
		}
		return listeTriee;
	}

	/**
	 * Search for the end of the set of heros in the LinkedList and position the iterator at this place
	 * @param iterateur
	 */
	private void trouverFinDesHeros(ListIterator<EtreVivant> iterateur) {
		EtreVivant captureResIt = null;
		boolean continuer = false;
		//We continue forward until we met a Hero
		while(iterateur.hasNext() && iterateur.next().getClass() == Heros.class) {}
		do {
			if(iterateur.hasPrevious()) {
				captureResIt = iterateur.previous();
				continuer = (captureResIt.getClass() == Homme.class);
			}
			else {
				continuer = false;
			}
		}
		while(continuer);
		if(captureResIt.getClass() == Heros.class) {
			iterateur.next();
		}
	}

	/**
	 * Search for the end of the set of homme in the LinkedList and position the iterator at this place
	 * @param iterateur
	 */
	private void trouverFinDesHommes(ListIterator<EtreVivant> iterateur) {
		while(iterateur.hasNext()) {
			iterateur.next();
		}
	}

	/**
	 * Returns a string containing the names of the Monster's camp in the natural order ie by names
	 * @return a string with the names of the monsters o the camp
	 */
	public String ordreNaturelMonstre() {
		NavigableSet<Monstre<?>> listeTrieeMonstre = new TreeSet<>();
		String chaine = "";
		for(Monstre<?> monstre : bataille.getCampMonstres()) {
			listeTrieeMonstre.add(monstre);
		}
		for(Monstre<?> monstre : listeTrieeMonstre) {
			chaine += monstre.getNom() + ", ";
		}
		return chaine;
	}

	/**
	 * Adds all of the monsters of the camp into the attribut monstreDomaineSet
	 */
	public void updateMonstresDomaine() {
		for(Monstre<?> monstre : bataille.getCampMonstres()) {
			monstresDomaineSet.add(monstre);
		}
	}
	
	/**
	 * Returns a string containing the list of monsters sorted by Domains
	 * @return a string with Domain and name of the monsters
	 */
	public String ordreMonstreDomaine() {
		String chaine = "";
		updateMonstresDomaine();
		int cptFeu = 0;
		int cptGlace = 0;
		int cptTranchant = 0;
		for(Monstre<?> monstre : monstresDomaineSet) {
			if(monstre.getDomaine() == Domaine.FEU && cptFeu == 0) {
				chaine += Domaine.FEU + "\n";
				cptFeu = 1;
			}
			if(monstre.getDomaine() == Domaine.GLACE && cptGlace == 0) {
				chaine += Domaine.GLACE + "\n";
				cptGlace = 1;
			}
			if(monstre.getDomaine() == Domaine.TRANCHANT && cptTranchant == 0) {
				chaine += Domaine.TRANCHANT + "\n";
				cptTranchant = 1;
			}
			chaine += monstre.getNom() + ", \n";
		}
		return chaine;
	}
	
	/**
	 * Adds all the monsters of the camp into the attribut monstresZoneSet
	 */
	public void updateMonstresZone() {
		for(Monstre<?> monstre : bataille.getCampMonstres()) {
			monstresZoneSet.add(monstre);
		}
	}
	
	/**
	 * Returns a string containing the list of monsters sorted by ZoneDeCombat
	 * @return a string with ZoneDeCombat and name of the monsters
	 */
	public String ordreMonstreZone() {
		String chaine = "";
		int cptTerre = 0;
		int cptAqua = 0;
		int cptAer = 0;
		updateMonstresZone();
		for(Monstre<?> monstre : monstresZoneSet) {
			if(monstre.getZoneDeCombat() == ZoneDeCombat.AERIEN && cptAer == 0) {
				chaine += ZoneDeCombat.AERIEN + "\n";
				cptAer = 1;
			}
			if(monstre.getZoneDeCombat() == ZoneDeCombat.AQUATIQUE && cptAqua == 0) {
				chaine += ZoneDeCombat.AQUATIQUE + "\n";
				cptAqua = 1;
			}
			if(monstre.getZoneDeCombat() == ZoneDeCombat.TERRESTRE && cptTerre == 0) {
				chaine += ZoneDeCombat.TERRESTRE + "\n";
				cptTerre = 1;
			}
			chaine += monstre.getNom()  + ":" + monstre.getForceDeVie() + ", \n";
		}
		return chaine;
	}

	/**
	 * Getter of the monstresDeFeu attribut
	 * @return monstresDeFeu
	 */
	public NavigableSet<Monstre<?>> getMonstresDeFeu() {
		updateMonstresDomaine();
		return monstresDeFeu;
	}

	/**
	 * Getter of the monstresDeGlace attribut
	 * @return monstresDeGlace
	 */
	public NavigableSet<Monstre<?>> getMonstresDeGlace() {
		updateMonstresDomaine();
		return monstresDeGlace;
	}

	/**
	 * Getter of the monstresTranchants attribut
	 * @return monstresTranchants
	 */
	public NavigableSet<Monstre<?>> getMonstresTranchants() {
		updateMonstresDomaine();
		return monstresTranchants;
	}
	
	/**
	 * Returns the first monster who is of domain dom
	 * @param dom
	 * @return
	 */
//	public Monstre<?> firstMonstreDomaine(Domaine dom){
//		Monstre<?> monstreReturn = null;
//		for(Monstre<?> monstre : monstresDomaineSet) {
//			if(monstre.getDomaine() == dom) {
//				monstreReturn = monstre;
//			}
//		}
//		return monstreReturn;
//	}
	
	/**
	 * Initialize a view of the monsters of domain FEU
	 */
//	public void initMonstresDeFeu() {
//		this.monstresDeFeu = monstresDomaineSet.headSet(firstMonstreDomaine(Domaine.GLACE), false);
//	}
	
	//Overwriting initMonstresDeFeu() method with degenerate Object:
	public void initMonstresDeFeu() {
		this.monstresDeFeu = monstresDomaineSet.headSet(new Monstre<>("", 0, null, Domaine.GLACE, (Pouvoir[])null), false);
	}
	
	/**
	 * Initialize a view of the monsters of domain GLACE
	 */
	public void initMonstresDeGlace() {
		Monstre<?> monsterDegeGlace = new Monstre<>("", 0, null, Domaine.GLACE, (Pouvoir[])null);
		Monstre<?> monsterDegeTranc = new Monstre<>("", 0, null, Domaine.TRANCHANT, (Pouvoir[])null);
		this.monstresDeGlace = monstresDomaineSet.subSet(monsterDegeGlace, true, monsterDegeTranc, false);
	}
	
	/**
	 * Initialize a view of the monsters of domain TRANCHANT
	 */
	public void initMonstresTranchant() {
		this.monstresTranchants = monstresDomaineSet.tailSet(new Monstre<>("", 0, null, Domaine.TRANCHANT, (Pouvoir[])null), false);
	}
	
	
	
}

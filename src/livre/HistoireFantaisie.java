package livre;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeSet;

import attaque.Arc;
import attaque.Boomerang;
import attaque.BouleDeFeu;
import attaque.Eclair;
import attaque.Epee;
import attaque.Feu;
import attaque.Glace;
import attaque.Griffe;
import attaque.LameAcier;
import attaque.LancePierre;
import attaque.Lave;
import attaque.Morsure;
import attaque.PicsDeGlace;
import attaque.Pouvoir;
import attaque.Tornade;
import attaque.Tranchant;
import bataille.Bataille;
import bataille.Grotte;
import bataille.GroupeHommes;
import bataille.Salle;
import protagoniste.Domaine;
import protagoniste.Heros;
import protagoniste.Homme;
import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;
import protagoniste.ZoneDeCombatNonCompatibleException;

public class HistoireFantaisie {
	protected Livre livre;
	private Map<String, List<Monstre<? extends Pouvoir>>> monstres = new HashMap<>();
	private Map<String, Homme> compagnie = new HashMap<>();
	private GroupeHommes groupeHommes = new GroupeHommes();
	private Salle salle;
	private Grotte grotte = new Grotte();

	public HistoireFantaisie(Livre livre) {
		this.livre = livre;
	}

	public void initialisationParDefaut(HistoireFantaisie histoireFantaisie) throws ZoneDeCombatNonCompatibleException{
		// Affectation de la sortie (ecran ou fichier)
		histoireFantaisie.configurationSortieDefaut();
		// Création des monstres
		histoireFantaisie.creationMonstresDefaut();
		// création de la grotte
		histoireFantaisie.creationGrotteDefaut();
		// configuration de la grotte
		histoireFantaisie.configurationGrotteDefaut();
		// Création du groupe d'hommes armés
		histoireFantaisie.creationCompagnieDefaut();
		histoireFantaisie.affectationArmesDefaut();
	}

	//Pour tester la classe Fichier: changer Ecran() par Fichier()
	private void configurationSortieDefaut() {
		livre = new Fichier();
	}

	//WARNING!!: changement des monstres vampiriens en un unique vampirien 
	public void creationMonstresDefaut() {
		List<Monstre<?>> listeDragotenebre = new ArrayList<>();
		listeDragotenebre.add(new Monstre<Feu>("dragotenebre", 200, ZoneDeCombat.AERIEN, Domaine.FEU, new BouleDeFeu(4),
				new Lave(1), new Eclair(3)));
		monstres.put("dragotenebre", listeDragotenebre);

		List<Monstre<?>> listeVampirien = new ArrayList<>();
		listeVampirien.add(new Monstre<Tranchant>("vampirien", 10, ZoneDeCombat.AERIEN, Domaine.TRANCHANT,
				new Morsure(10)));
		monstres.put("vampirien", listeVampirien);

		List<Monstre<?>> listeMarinsangant = new ArrayList<>();
		listeMarinsangant.add(new Monstre<Glace>("marinsangant", 150, ZoneDeCombat.AQUATIQUE, Domaine.GLACE,
				new PicsDeGlace(10), new Tornade(1)));
		monstres.put("marinsangant", listeMarinsangant);

		List<Monstre<?>> listeGuillotimort = new ArrayList<>();
		listeGuillotimort.add(new Monstre<Tranchant>("guillotimort", 80, ZoneDeCombat.TERRESTRE, Domaine.TRANCHANT,
				new LameAcier(10), new Griffe()));
		monstres.put("guillotimort", listeGuillotimort);

		List<Monstre<?>> listeGivrogolem = new ArrayList<>();
		listeGivrogolem.add(new Monstre<Glace>("givrogolem", 200, ZoneDeCombat.TERRESTRE, Domaine.GLACE,
				new PicsDeGlace(10), new Tornade(1)));
		monstres.put("givrogolem", listeGivrogolem);

		List<Monstre<?>> listeAqualave = new ArrayList<>();
		listeAqualave.add(new Monstre<Feu>("aqualave", 30, ZoneDeCombat.AQUATIQUE, Domaine.FEU, new Lave(5)));
		monstres.put("aqualave", listeAqualave);

		List<Monstre<?>> listeRequispectre = new ArrayList<>();
		listeRequispectre.add(
				new Monstre<Tranchant>("requispectre", 200, ZoneDeCombat.AQUATIQUE, Domaine.TRANCHANT, new Griffe()));
		monstres.put("requispectre", listeRequispectre);

		List<Monstre<?>> listeSoufflemort = new ArrayList<>();
		listeSoufflemort
		.add(new Monstre<Glace>("soufflemort", 120, ZoneDeCombat.AERIEN, Domaine.GLACE, new Tornade(8)));
		monstres.put("soufflemort", listeSoufflemort);

		List<Monstre<?>> listeCramombre = new ArrayList<>();
		listeCramombre.add(new Monstre<Feu>("cramombre", 80, ZoneDeCombat.TERRESTRE, Domaine.FEU, new BouleDeFeu(2),
				new Lave(1), new Eclair(1)));
		monstres.put("cramombre", listeCramombre);
	}

	public void creationGrotteDefaut() throws ZoneDeCombatNonCompatibleException {
		ajouterSalle(ZoneDeCombat.TERRESTRE, "guillotimort");
		ajouterSalle(ZoneDeCombat.AERIEN, "dragotenebre");
		ajouterSalle(ZoneDeCombat.TERRESTRE, "cramombre");
		ajouterSalle(ZoneDeCombat.TERRESTRE, "givrogolem");
		ajouterSalle(ZoneDeCombat.AERIEN, "vampirien");
		ajouterSalle(ZoneDeCombat.AQUATIQUE, "aqualave");
		ajouterSalle(ZoneDeCombat.AQUATIQUE, "marinsangant");
		ajouterSalle(ZoneDeCombat.AQUATIQUE, "requispectre");
		ajouterSalle(ZoneDeCombat.AERIEN, "soufflemort");
	}

	private void ajouterSalle(ZoneDeCombat zoneDeCombat, String nomMonstre) {
		List<Monstre<? extends Pouvoir>> listeMonstres = monstres.get(nomMonstre);
		int tailleTableau = listeMonstres.size();
		Monstre<? extends Pouvoir>[] tableauMonstre = new Monstre<?>[tailleTableau];
		tableauMonstre = listeMonstres.toArray(tableauMonstre);
		try {
			grotte.ajouterSalle(zoneDeCombat, tableauMonstre);
		} catch (ZoneDeCombatNonCompatibleException e) {
			e.printStackTrace();
		}
	}

	public void configurationGrotteDefaut() {
		grotte.configurerAcces(1, 2, 6);
		grotte.configurerAcces(2, 1, 3, 5);
		grotte.configurerAcces(3, 2, 4);
		grotte.configurerAcces(4, 3, 5, 9);
		grotte.configurerAcces(5, 2, 4, 6, 8);
		grotte.configurerAcces(6, 1, 5, 7);
		grotte.configurerAcces(7, 6, 8);
		grotte.configurerAcces(8, 5, 7, 9);
		grotte.configurerAcces(9, 4, 8);

		grotte.setNumeroSalleDecisive(9);
		salle = grotte.premiereSalle();
	}

	public void creationCompagnieDefaut() {
		Homme thomas = new Homme("Thomas");
		compagnie.put("thomas", thomas);
		Homme louis = new Homme("Louis");
		compagnie.put("louis", louis);
		Heros arthur = new Heros("Arthur");
		compagnie.put("arthur", arthur);
		Heros roland = new Heros("Roland");
		compagnie.put("roland", roland);

		groupeHommes.ajouterHommes(thomas, louis, arthur, roland);
	}

	public void affectationArmesDefaut() {
		Homme thomas = compagnie.get("thomas");
		thomas.ajouterArmes(new LancePierre(), new Boomerang());
		Homme louis = compagnie.get("louis");
		louis.ajouterArmes(new LancePierre(), new Arc(5));
		Homme arthur = compagnie.get("arthur");
		arthur.ajouterArmes(new Epee("excalibur"), new Arc(8));
		Homme roland = compagnie.get("roland");
		roland.ajouterArmes(new Epee("durandal"), new Arc(3));
	}

	public void ecrireHistoire() {
	}

	public void afficherLesMonstres() {
		StringBuilder affichage = new StringBuilder();
		Collection<Bataille> batailles = this.grotte.getBatailles().values();
		NavigableSet<Monstre<? extends Pouvoir>> setDeMonstres = new TreeSet<>();
		List<Monstre<? extends Pouvoir>> listeDesMonstres = new LinkedList<>();
		//Implementation du pipeline
		batailles.stream().forEach(batailleElement ->{ 
			listeDesMonstres.addAll(batailleElement.getCampMonstres().getListe());
			for(Monstre<? extends Pouvoir> monstre : listeDesMonstres) {
				setDeMonstres.add(monstre);
			}});
		//Sans implementation du pipeline
//		for(Bataille bataille : batailles) {
//			listeDesMonstres.addAll(bataille.getCampMonstres().getListe());
//			for(Monstre<? extends Pouvoir> monstre : listeDesMonstres) {
//				setDeMonstres.add(monstre);
//			}
//		}
		for(Iterator<Monstre<? extends Pouvoir>> iterator = setDeMonstres.iterator() ; iterator.hasNext();) {
			Monstre<? extends Pouvoir> monstre = iterator.next();
			affichage.append(monstre.getNom());
			if(iterator.hasNext()) {
				affichage.append(", ");
			}
		}
		affichage.append(".");
		livre.ecrire(affichage.toString());
	}



	public static void main(String[] args) throws ZoneDeCombatNonCompatibleException {
		Livre livre = new Ecran();
		HistoireFantaisie histoireFantaisie = new HistoireFantaisie(livre);
		// Initialisation par defaut
		histoireFantaisie.initialisationParDefaut(histoireFantaisie);
		// Début de l'affrontement
		histoireFantaisie.ecrireHistoire();
		histoireFantaisie.afficherLesMonstres();
	}
}

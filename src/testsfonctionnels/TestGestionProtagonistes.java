package testsfonctionnels;

import java.util.NavigableSet;

import attaque.BouleDeFeu;
import attaque.Eclair;
import attaque.Feu;
import attaque.Glace;
import attaque.Griffe;
import attaque.LameAcier;
import attaque.Lave;
import attaque.Morsure;
import attaque.PicsDeGlace;
import attaque.Pouvoir;
import attaque.Tornade;
import attaque.Tranchant;
import bataille.Bataille;
import bataille.Camp;
import livre.AideEcrivain;
import protagoniste.Domaine;
import protagoniste.Heros;
import protagoniste.Homme;
import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;

public class TestGestionProtagonistes {
	public static String affichageMonstres(NavigableSet<Monstre<? extends Pouvoir>> monstres) {
		StringBuilder affichage = new StringBuilder();
		for(Monstre<? extends Pouvoir> monstre : monstres) {
			affichage.append(monstre.getNom() + " monstre de " + monstre.getDomaine() + ", ");
		}
		return affichage.toString();
	}
	
	public static void main(String[] args) {
		Monstre<Feu> dragotenebre = new Monstre<>("dragotenebre", 200, ZoneDeCombat.AERIEN, Domaine.FEU,
				new BouleDeFeu(4), new Lave(1), new Eclair(3));
		Monstre<Tranchant> vampirien = new Monstre<>("vampirien", 10, ZoneDeCombat.AERIEN, Domaine.TRANCHANT,
				new Morsure(10));
		Monstre<Glace> marinsangant = new Monstre<>("marinsangant", 150, ZoneDeCombat.AQUATIQUE, Domaine.GLACE,
				new PicsDeGlace(10), new Tornade(1));
		Monstre<Tranchant> guillotimort = new Monstre<>("guillotimort", 80, ZoneDeCombat.TERRESTRE, Domaine.TRANCHANT,
				new LameAcier(10), new Griffe());
		Homme thomas = new Homme("Thomas");
		Homme louis = new Homme("Louis");
		Heros arthur = new Heros("Arthur");
		Heros archibald = new Heros("Archibald");
		Homme alain = new Homme("Alain");

		Bataille bataille = new Bataille();
		thomas.rejointBataille(bataille);
		louis.rejointBataille(bataille);
		arthur.rejointBataille(bataille);
		archibald.rejointBataille(bataille);
		alain.rejointBataille(bataille);
		dragotenebre.rejointBataille(bataille);
		vampirien.rejointBataille(bataille);
		marinsangant.rejointBataille(bataille);
		guillotimort.rejointBataille(bataille);

		Camp<Homme> campsHumain = bataille.getCampHumains();
		Camp<Monstre<? extends Pouvoir>> campsMonstre = bataille.getCampMonstres();

		System.out.println("**** TP2 ****");
		System.out.println("\ncamps des humains :\n" + campsHumain);
		System.out.println("\ncamps des monstres :\n" + campsMonstre);
		
		AideEcrivain aideEcrivain = new AideEcrivain(bataille);
	    System.out.println("visualisation des forces humaines :\n" + aideEcrivain.visualiserForcesHumaines());
	    
	    System.out.println("\n**** TP3 ****");
		System.out.println("\nordre naturel :\n" + aideEcrivain.ordreNaturelMonstre());
		
		/*
		 * Resultat attendu
		 * ordre naturel :
		 * dragotenebre, guillotimort, marinsangant, vampirien
		 * */
		
		System.out.println("\nordre par domaine :\n" + aideEcrivain.ordreMonstreDomaine());
		/**
		 * Resultat attendu:
		 * 
		 * ordre par domaine :
		 * FEU :
		 * dragotenebre,
		 * GLACE :
		 * marinsangant,
		 * TRANCHANT :
		 * guillotimort, vampirien
		 */
		
		System.out.println("\nordre par zone de combat :\n" + aideEcrivain.ordreMonstreZone());
		
		/**
		 * resultat attendu
		 * ordre par zone de combat :
		 * AERIEN :
		 * dragotenebre : 200, vampirien : 10,
		 * AQUATIQUE :
		 * marinsangant : 150,
		 * TERRESTRE :
		 * guillotimort : 80
		 */
		
		aideEcrivain.initMonstresDeFeu();
		NavigableSet<Monstre<? extends Pouvoir>> monstres = aideEcrivain.getMonstresDeFeu();
		String affichage = affichageMonstres(monstres);
		System.out.println("\nmonstres de feu :\n" + affichage);
		
		/**
		 * Resultat attendu:
		 * monstres de feu :
		 * dragotenebre monstre de FEU
		 */
		
		Monstre<Glace> soufflemort = new Monstre<>("soufflemort", 120, ZoneDeCombat.AERIEN, Domaine.GLACE,
				new Tornade(8));
		Monstre<Feu> cramombre = new Monstre<>("cramombre", 80, ZoneDeCombat.TERRESTRE, Domaine.FEU, new BouleDeFeu(2),
				new Lave(1), new Eclair(1));
		
		soufflemort.rejointBataille(bataille);
		cramombre.rejointBataille(bataille);
		
		monstres = aideEcrivain.getMonstresDeFeu();
		affichage = affichageMonstres(monstres);
		System.out.println("\nmonstres de feu :\n" + affichage);
		
		/**
		 * Resultat attendu:
		 * monstres de feu :
		 * cramombre monstre de FEU, dragotenebre monstre de FEU
		 */
		
		Monstre<Glace> givrogolem = new Monstre<>("givrogolem", 200, ZoneDeCombat.TERRESTRE, Domaine.GLACE,
				new PicsDeGlace(10), new Tornade(1));
		givrogolem.rejointBataille(bataille);

		monstres = aideEcrivain.getMonstresDeFeu();
		affichage = affichageMonstres(monstres);
		System.out.println("\nmonstres de feu :\n" + affichage);
		
		/**
		 * Resultat attendu: (sans objet degenere)
		 * monstres de feu :
		 * cramombre monstre de FEU, dragotenebre monstre de FEU, givrogolem monstre de GLACE
		 */
		
		/**
		 * Resultat attendu: (avec objet degenere)
		 * monstres de feu :
		 * cramombre monstre de FEU, dragotenebre monstre de FEU
		 */
		System.out.println("Dernier resultat attendu");
		
		aideEcrivain.initMonstresDeGlace();
		aideEcrivain.initMonstresTranchant();

		Monstre<Feu> aqualave = new Monstre<>("aqualave", 30, ZoneDeCombat.AQUATIQUE, Domaine.FEU, new Lave(5));

		Monstre<Tranchant> requispectre = new Monstre<>("requispectre", 200, ZoneDeCombat.AQUATIQUE, Domaine.TRANCHANT,
				new Griffe());

		aqualave.rejointBataille(bataille);
		requispectre.rejointBataille(bataille);

		monstres = aideEcrivain.getMonstresDeFeu();
		affichage = affichageMonstres(monstres);
		System.out.println("\nmonstres de feu :\n" + affichage);

		monstres = aideEcrivain.getMonstresDeGlace();
		affichage = affichageMonstres(monstres);
		System.out.println("\nmonstres de glace :\n" + affichage);

		monstres = aideEcrivain.getMonstresTranchants();
		affichage = affichageMonstres(monstres);
		System.out.println("\nmonstres tranchant :\n" + affichage);
		
		/**
		 * Resultat attendus:
		 * 
		 * monstres de feu :
		 * aqualave monstre de FEU, cramombre monstre de FEU, dragotenebre monstre de FEU
		 * monstres de glace :
		 * givrogolem monstre de GLACE, marinsangant monstre de GLACE, soufflemort monstre de GLACE
		 * monstres tranchant :
		 * guillotimort monstre de TRANCHANT, requispectre monstre de TRANCHANT, vampirien monstre de TRANCHANT
		 */
		
	}
}

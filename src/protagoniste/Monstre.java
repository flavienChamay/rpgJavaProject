	package protagoniste;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import attaque.Pouvoir;
import bataille.Bataille;

public class Monstre<P extends Pouvoir> extends EtreVivant{
	private P[] attaques;
	private ZoneDeCombat zoneDeCombat;
	private Domaine domaine;
	private GestionAttaque gestionAttaque;
	
	/**
	 * Constructor of the class Monstre
	 * @param nom
	 * @param forceDeVie
	 * @param zoneDeCombat
	 * @param domaine
	 * @param attaques
	 */
	@SafeVarargs
	public Monstre(String nom, int forceDeVie, ZoneDeCombat zoneDeCombat, Domaine domaine, P... attaques) {
		super(nom, forceDeVie);
		this.attaques = attaques;
		this.zoneDeCombat = zoneDeCombat;
		this.domaine = domaine;
	}

	/**
	 * Getter of the combat zone of the monster
	 * @return combat zone of the monster
	 */
	public ZoneDeCombat getZoneDeCombat() {
		return zoneDeCombat;
	}

	/**
	 * Getter of the domain of the monster
	 * @return the domain of the monster
	 */
	public Domaine getDomaine() {
		return domaine;
	}
	
	@Override
	public String toString() {
		return "Monstre [getNom()=" + getNom() + " ,attaques=" + Arrays.toString(attaques) + ", zoneDeCombat=" + zoneDeCombat + ", getForceDeVie()=" + getForceDeVie() + "]";
	}

	/**
	 * Intern Class of Monstre who manages the attacks of a monster
	 * @author magicuser
	 *
	 */
	private static class GestionAttaque implements Iterator<Pouvoir> {
		//Attention attaquesPossibles est inutile car c'est la meme chose que attaques
		//On demande aussi de mettre un private static dans le cours...
		private Pouvoir[] attaquesPossibles;
		private int nbAttaquesPossibles;
		
		public GestionAttaque(Pouvoir[] attaques) {
			this.attaquesPossibles = attaques;
			this.nbAttaquesPossibles = attaquesPossibles.length;
		}
		
		/**
		 * For all attacks, if it is not operational then the current attack is replaced by the nbAttaquesPossible - 1
		 * @return True if nbAttaquesPossibles is positive, False if not
		 */
		@Override
		public boolean hasNext() {
			for (int i = 0; i < nbAttaquesPossibles; i++) {
				if(! attaquesPossibles[i].isOperationnel()) {
					attaquesPossibles[i] = attaquesPossibles[nbAttaquesPossibles-1];
					nbAttaquesPossibles--;
				}
			}
			return (nbAttaquesPossibles > 0);
		}

		/**
		 * Returns a random attack
		 * @return a random attack in the array attaques
		 */
		@Override
		public Pouvoir next() {
			Random rand = new Random();
			int nbAlea = rand.nextInt(nbAttaquesPossibles);
			return (attaquesPossibles[nbAlea]);
		}
	}
	
	/**
	 * Regenerates all powers of the array attaques and create an instance of the GestionAttaque class
	 */
	public void entreEnCombat() {
		for(int i = 0; i < attaques.length; i++) {
			attaques[i].regenerePouvoir();
		}
		this.gestionAttaque = new GestionAttaque(attaques);
	}
	
	/**
	 * Returns the next attack if available, returns null if not
	 * @return Returns the next attack if available, returns null if not
	 */
	public Pouvoir attaque() {
		if(gestionAttaque.hasNext()) {
			return (gestionAttaque.next());
		}
		else {
			return null;
		}	
	}
	
	/**
	 * The monster joins the battle
	 * @param bataille
	 */
	public void rejointBataille(Bataille bataille) {
		bataille.ajouter(this);
	}
	
	/**
	 * Implementation of the abstract method mourir, the monster dies
	 */
	public void mourir() {
		this.bataille.eliminer(this);
	}
	
	public boolean attaqueHomme(Homme homme, Pouvoir pouvoir) {
		int pointDeDegat = pouvoir.utiliser();
		Random rand = new Random();
		boolean attaqueReussi = rand.nextBoolean();
		if(attaqueReussi) {
			homme.subitAttaque(pointDeDegat);
		}
		return attaqueReussi;
	}
}

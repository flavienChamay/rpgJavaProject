package protagoniste;

import bataille.Bataille;

public abstract class EtreVivant implements Comparable<EtreVivant>{
	protected String nom;
	protected int forceDeVie;
	protected Bataille bataille;
	
	/**
	 * Constructor of class EtreVivant
	 * @param nom
	 * @param forceDeVie
	 */
	public EtreVivant(String nom, int forceDeVie) {
		this.nom = nom;
		this.forceDeVie = forceDeVie;
	}

	/**
	 * Getter of the name of the etre vivant
	 * @return name of the etre vivant
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Getter of the life points of the etre vivant
	 * @return amount of life points of the etre vivant
	 */
	public int getForceDeVie() {
		return forceDeVie;
	}

	@Override
	public String toString() {
		return "EtreVivant [nom=" + nom + ", forceDeVie=" + forceDeVie + "]";
	}
	
	/**
	 * The Etre Vivant joins the battle
	 * @param bataille
	 */
	public void rejointBataille(Bataille bataille) {
		this.bataille = bataille;
	}
	
	/**
	 * Abstract method mourir, all Etre vivant dies, eventually
	 */
	public abstract void mourir();
	
	/**
	 * Method equals override
	 * Two EtreVivant are the same if and only if they have the same name
	 * Uses compareTo method
	 */
	public boolean equals(Object obj) {
		if(obj instanceof EtreVivant) {
			EtreVivant etre = (EtreVivant) obj;
			return (etre.getNom().compareTo(this.nom) == 0);
		}
		return false;
	}

	/**
	 * Method compareTo override according to name order
	 * @return 0 if etreA = etreB, negative int if etreA < etreToCompare and positive int if etreA > etreToCompare 
	 */
	@Override
	public int compareTo(EtreVivant etreToCompare) {
		return this.nom.compareTo(etreToCompare.nom);
	}
	
	public void subitAttaque(int pointDeDegat) {
		this.forceDeVie = this.forceDeVie - pointDeDegat;
		if(forceDeVie < 1) {
			mourir();
		}
	}
	
	/**
	 * Reourne vrai si etre vivant toujours en vie 
	 * sinon faux
	 * @return
	 */
	public boolean enVie() {
		return this.forceDeVie > 1;
	}
}

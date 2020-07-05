package attaque;

public abstract class Pouvoir extends ForceDeCombat {
	private int nbUtilisationPouvoir;
	private int nbUtilisationPouvoirInitial;

	/**
	 * Constructor of the Pouvoir class
	 * @param pointDeDegat
	 * @param nom
	 * @param nbUtilisationPouvoir
	 */
	public Pouvoir(int pointDeDegat, String nom, int nbUtilisationPouvoir) {
		super(pointDeDegat, nom);
		this.nbUtilisationPouvoir = nbUtilisationPouvoir;
	}

	/**
	 * The pouvoir is again operational
	 * If operationnel = false then operationnel = true and the power is reinitialized to nbUtilisationInitial
	 * Nothing happens if operationnel = true
	 */
	public void regenerePouvoir() {
		if(this.isOperationnel() != true) {
			toggleOperationnel();
			nbUtilisationPouvoir = nbUtilisationPouvoirInitial;
		}
	}

	/**
	 * If the pouvoir is operational then decrease the nbUtilisationPouvoir by 1
	 * If nbUtilisationPouvoir = 0 then the pouvoir is not operational
	 * @return the amount of damage of the pouvoir
	 */
	public int utiliser() {
		if(nbUtilisationPouvoir == 0) {
			toggleOperationnel();
		}
		else {
			if(isOperationnel() == true) {
				nbUtilisationPouvoir--;
			}
		}
		return utiliser();
	}

	public String afficherPouvoir() {
		StringBuilder affichage = new StringBuilder();
		if (!operationnel) {
			affichage.append("Il ne pourra plus utiliser " + getNom() + " pendant longtemps.");
		}
		else {
			affichage.append("Il peut encore utiliser ce pouvoir " + nbUtilisationPouvoir + " fois.");
		}
		return affichage.toString();
	}
}

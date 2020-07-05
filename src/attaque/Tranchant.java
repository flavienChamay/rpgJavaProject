package attaque;

public abstract class Tranchant extends Pouvoir{
	
	/**
	 * Constructor of class Tranchant
	 * @param pointDeDegat
	 * @param nom
	 * @param nbUtilisationPouvoir
	 */
	public Tranchant(int pointDeDegat, String nom, int nbUtilisationPouvoir) {
		super(pointDeDegat, nom, nbUtilisationPouvoir);
	}
	
}

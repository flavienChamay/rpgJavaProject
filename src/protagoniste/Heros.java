package protagoniste;

public class Heros extends Homme{

	/**
	 * Constructor of the class Heros
	 * @param nom
	 */
	public Heros(String nom) {
		super(nom);
		this.forceDeVie = 100;
	}

	@Override
	public String toString() {
		return "Heros [nom=" + nom + ", forceDeVie=" + forceDeVie + "]";
	}
	
}

package attaque;

public abstract class ForceDeCombat {
	private String nom;
	private int pointDeDegat;
	protected boolean operationnel;
	
	/**
	 * Constructor of ForceDeCombat class
	 * @param pointDeDegat
	 * @param nom
	 */
	public ForceDeCombat(int pointDeDegat, String nom) {
		this.pointDeDegat = pointDeDegat;
		this.nom = nom;
		this.operationnel = true;
	}

	/**
	 * Getter of point de degat attribut
	 * @return the amount of damage of the force de combat
	 */
	public int utiliser() {
		return pointDeDegat;
	}

	/**
	 * getter of nom attribut
	 * @return the name of the force de combat
	 */
	public String getNom() {
		return nom;
	}

	@Override
	public String toString() {
		return "ForceDeCombat [nom=" + nom + ", pointDeDegat=" + pointDeDegat + "]";
	}

	/**
	 * Getter of the operationnel attribut
	 * @return True if the force de combat is available, False if not
	 */
	public boolean isOperationnel() {
		return operationnel;
	}
	
	/**
	 * Changes the value of operationnel attribut
	 * If operationnel = true then operationnel = false
	 * etc...
	 */
	protected void toggleOperationnel() {
		this.operationnel = !operationnel;
	}

	/**
	 * Getter of the point of degat
	 * @return
	 */
	public int getPointDeDegat() {
		return pointDeDegat;
	}
	
}

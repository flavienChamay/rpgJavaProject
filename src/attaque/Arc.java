package attaque;

import protagoniste.ZoneDeCombat;

public class Arc extends Arme{
	private int nbFlechesRestantes;

	/**
	 * Constructor of Arc class
	 * @param nbFlechesRestantes
	 */
	public Arc(int nbFlechesRestantes) {
		super(50, "Arc", ZoneDeCombat.AQUATIQUE, ZoneDeCombat.TERRESTRE, ZoneDeCombat.AERIEN);
		this.nbFlechesRestantes = nbFlechesRestantes;
	}
	
	/**
	 * Redefinition of isOperationnel for Arc class
	 * @return True if nbFLechesRestantes > 0 and isOperationnel equals true, False if not
	 */
	@Override
	public boolean isOperationnel() {
		return (nbFlechesRestantes > 0) && super.isOperationnel();
	}
	
	/**
	 * Returns PointDeDegat of Arc. If nbFlechesRestantes equals 0 then non operationnal
	 * If there are arrows, decreases nbFlechesRestantes by 1
	 * @return the amount of damage done by the arc
	 */
	public int utiliser() {
		if(isOperationnel()) {
			this.nbFlechesRestantes--;
		}
		else {
			toggleOperationnel();
		}
		return getPointDeDegat();
	}
}

package bataille;

import protagoniste.ZoneDeCombat;

public class Salle {
	private int numSalle;
	private ZoneDeCombat zoneDeCombat;
	
	/**
	 * Constructor of the class Salle
	 * @param numSalle
	 * @param zoneDeCombat
	 */
	public Salle(int numSalle, ZoneDeCombat zoneDeCombat) {
		this.numSalle = numSalle;
		this.zoneDeCombat = zoneDeCombat;
	}

	/**
	 * Getter of the number of the room
	 * @return
	 */
	public int getNumSalle() {
		return numSalle;
	}

	/**
	 * Getter of the combat zone of the room
	 * @return
	 */
	public ZoneDeCombat getZoneDeCombat() {
		return zoneDeCombat;
	}

	@Override
	public String toString() {
		return "Salle no " + numSalle + " de type combat " + zoneDeCombat;
	}
}

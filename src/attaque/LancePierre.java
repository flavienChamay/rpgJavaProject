package attaque;

import protagoniste.ZoneDeCombat;

public class LancePierre extends Arme{

	/**
	 * Constructor of LancePierre class
	 * @param pointDeDegat
	 * @param nom
	 */
	public LancePierre() {
		super(10, "Lance-pierre", ZoneDeCombat.AERIEN, ZoneDeCombat.TERRESTRE);
	}
	
}

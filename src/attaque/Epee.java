package attaque;

import protagoniste.ZoneDeCombat;

public class Epee extends Arme{
	private String nomEpee;
	
	/**
	 * Constructor of Epee class
	 * @param nomEpee
	 */
	public Epee(String nomEpee) {
		super(80, "Epee", ZoneDeCombat.AQUATIQUE, ZoneDeCombat.TERRESTRE);
		this.nomEpee = nomEpee;
	}

	/**
	 * Getter of name of the sword
	 * @return name of the sword
	 */
	public String getNomEpee() {
		return nomEpee;
	}

}

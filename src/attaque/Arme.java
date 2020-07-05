package attaque;

import java.util.HashSet;

import protagoniste.ZoneDeCombat;

public abstract class Arme extends ForceDeCombat implements Orderable<Arme>{
	private HashSet<ZoneDeCombat> zonesDeCombat = new HashSet<>();
	/**
	 * Constructor of the Arme class
	 * @param pointDeDegat
	 * @param nom
	 */
	public Arme(int pointDeDegat, String nom, ZoneDeCombat... zDC) {
		super(pointDeDegat, nom);
		for(ZoneDeCombat zoneDeCombat : zDC) {
			zonesDeCombat.add(zoneDeCombat);
		}
	}
	/**
	 * Getter on zonesDeCombat
	 * @return
	 */
	public HashSet<ZoneDeCombat> getZonesDeCombat() {
		return zonesDeCombat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zonesDeCombat == null) ? 0 : zonesDeCombat.hashCode()); 
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Arme) {
			Arme arme = (Arme) obj;
			return (this.isOperationnel() == arme.isOperationnel()) 
					&& (this.getPointDeDegat() == arme.getPointDeDegat()
					&& (this.getNom().equals(arme.getNom())));
		}
		return false;
	}
	
	@Override
	public int compareTo(Arme armeToCompare) {
		if(this.isOperationnel() == armeToCompare.isOperationnel()) {
			if(this.getPointDeDegat() == armeToCompare.getPointDeDegat()) {
				return this.getNom().compareTo(armeToCompare.getNom());
			}
			return (this.getPointDeDegat() < armeToCompare.getPointDeDegat()) ? 1 : -1;
		}
		return (this.isOperationnel()) ? -1 : 1;
	}
}

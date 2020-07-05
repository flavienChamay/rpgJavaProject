package bataille;

import attaque.Pouvoir;
import protagoniste.Homme;
import protagoniste.Monstre;

public class Bataille {
	private Camp<Homme> campHumains = new Camp<Homme>();
	private Camp<Monstre<? extends Pouvoir>> campMonstres = new Camp<Monstre<? extends Pouvoir>>();
	
	/**
	 * Add a human to the human camp
	 * @param homme
	 */
	public void ajouter(Homme homme) {
		campHumains.ajouter(homme);
	}
	
	/**
	 * Add a monster to the monster camp
	 * @param monstre
	 */
	public void ajouter(Monstre<? extends Pouvoir>  monstre) {
		campMonstres.ajouter(monstre);
	}
	
	/**
	 * Delete a human in the human camp
	 * @param homme
	 */
	public void eliminer(Homme homme) {
		campHumains.eliminer(homme);
	}
	
	/**
	 * Delete a monster in the monster camp 
	 * @param monstre
	 */
	public void eliminer(Monstre<? extends Pouvoir>  monstre) {
		campMonstres.eliminer(monstre);
	}

	/**
	 * Getter of the human camp
	 * @return human camp
	 */
	public Camp<Homme> getCampHumains() {
		return campHumains;
	}

	/**
	 * Getter of the monster camp
	 * @return monster camp
	 */
	public Camp<Monstre<? extends Pouvoir>> getCampMonstres() {
		return campMonstres;
	}
}

package protagoniste;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import attaque.Arme;
import attaque.Pouvoir;
import bataille.Bataille;
import protagoniste.Heros;

public class Homme extends EtreVivant{
	private Map<ZoneDeCombat, List<Arme>> armes = new EnumMap<>(ZoneDeCombat.class);
	private Arme armeChoisie;

	/**
	 * Constructor of the Homme class
	 * @param nom
	 */
	public Homme(String nom) {
		super(nom, 70);
	}
	
	/**
	 * The human joins the battle
	 * @param bataille
	 */
	public void rejointBataille(Bataille bataille) {
		bataille.ajouter(this);
	}
	
	/**
	 * Implementation of the abstract method mourir, the human dies
	 */
	public void mourir() {
		this.bataille.eliminer(this);
	}

	@Override
	public String toString() {
		return "Homme [nom=" + nom + ", forceDeVie=" + forceDeVie + "]";
	}
	
	/**
	 * Getter of the arme choisie
	 * @return
	 */
	public Arme getArmeChoisie() {
		return armeChoisie;
	}

	/**
	 * Ajoute les armes dans chaque liste ou elle est efficace
	 * @param armeAAjouter
	 */
	public void ajouterArmes(Arme... armeAAjouter) {
		for(Arme armeIt : armeAAjouter) {
			Set<ZoneDeCombat> zonesDeCombat = armeIt.getZonesDeCombat();
			for(ZoneDeCombat zoneDeCombat : zonesDeCombat) {
				List<Arme> listeArme = new ArrayList<>();
				if(armes.containsKey(zoneDeCombat)) {
					listeArme = armes.get(zoneDeCombat);
					listeArme.add(armeIt);
				}
				else {
					listeArme.add(armeIt);
					armes.put(zoneDeCombat, listeArme);
				}
			}
		}
	}
	
	/**
	 * Supprime une arme
	 * @param armeASupprimer
	 */
	public void supprimerArme(Arme armeASupprimer) {
		for(List<Arme> arme : armes.values()) {
			arme.remove(armeASupprimer);
		}
	}
	
	/**
	 * 
	 * @param monstreACombattre
	 * @return
	 */
	public Arme choisirArme(Monstre<? extends Pouvoir> monstreACombattre) {
		ZoneDeCombat zoneDeCombat = monstreACombattre.getZoneDeCombat();
		if(this.armes.containsKey(zoneDeCombat)) {
			List<Arme> listeArmes = this.armes.get(zoneDeCombat);
			if(listeArmes.isEmpty()) {
				return this.armeChoisie = null;
			}
			NavigableSet<Arme> armesTriees = new TreeSet<>(listeArmes);
			int pointDeVieMonstre = monstreACombattre.getForceDeVie();
			NavigableSet<Arme> armesAdaptees = armesTriees.tailSet(new KeyArme(pointDeVieMonstre, zoneDeCombat), true);
			if(!armesAdaptees.isEmpty()) {
				return this.armeChoisie = armesAdaptees.first();
			}
			else {
				return this.armeChoisie = armesTriees.last();
			}
		}
		return this.armeChoisie = null;
	}
	
	/**
	 * Classe dégénerée de la clé de l'arme choisie
	 * @author magicuser
	 *
	 */
	public class KeyArme extends Arme{
		public KeyArme(int pointDeDegat, ZoneDeCombat... zoneDeCombat) {
			super(pointDeDegat, "", zoneDeCombat);
		}
	}
	
	public boolean attaqueReussi() {
		Random rand = new Random();
		int nbRand = rand.nextInt(20);
		if(getClass() == Homme.class) {
			return nbRand < 10;
		}
		else {
			return nbRand < 14;
		}
	}
	
	public boolean attaqueMonstre(Monstre<? extends Pouvoir> monstre) {
		int pointDeDegat = armeChoisie.utiliser();
		boolean atkSuccess = attaqueReussi();
		if(atkSuccess) {
			subitAttaque(pointDeDegat);
		}
		return atkSuccess;
	}
}

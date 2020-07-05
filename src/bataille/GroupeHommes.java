package bataille;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

import attaque.Arme;
import attaque.Pouvoir;
import protagoniste.Homme;
import protagoniste.Monstre;

public class GroupeHommes {
	private NavigableSet<Homme> groupe = new TreeSet<>();
	
	/**
	 * Add a bunch of Homme to the groupe
	 * @param hommes
	 */
	public void ajouterHommes(Homme... hommes) {
		for(Homme homme : hommes) {
			groupe.add(homme);
		}
	}
	
	/**
	 * Class who compares Hommes with their life points, if equals, 
	 * it compares the name by alphabetic order 
	 * @author magicuser
	 *
	 */
//	private static class ComparateurHommes implements Comparator<Homme>{
//		@Override
//		public int compare(Homme homme1, Homme homme2) {
//			if(homme1.getForceDeVie() > homme2.getForceDeVie()) {
//				return -1;
//			}
//			else if(homme1.getForceDeVie() < homme2.getForceDeVie()) {
//				return 1;
//			}
//			else if(homme1.getNom().compareTo(homme2.getNom()) < 0){
//				return -1;
//			}
//			else if(homme1.getNom().compareTo(homme2.getNom()) > 0) {
//				return 1;
//			}
//			return 0;
//		}
//	}
	
	//Implantation anonyme de GroupeHommes
//	private static class ComparateurHommes implements TreeSet<>(homme1, homme2) -> homme1.getForceDeVie() - homme2.getForceDeVie()
	
	/**
	 * Class who compares weapons
	 * @author magicuser
	 *
	 */
	private static class ComparateurArmes implements Comparator<Arme>{
		Monstre<?> monstre;
		
		public ComparateurArmes(Monstre<? extends Pouvoir> monstre) {
			this.monstre = monstre;
		}
		
		@Override
		public int compare(Arme arme1, Arme arme2) {
			int poindDeVieMonstre = this.monstre.getForceDeVie();
			if(arme1 == null) {
				return 0;
			}
			int arme1Damage = arme1.getPointDeDegat();
			int arme2Damage = arme2.getPointDeDegat();
			if(arme1Damage != arme2Damage) {
				Map<Integer, Arme> classementForce = new TreeMap<>();
				classementForce.put(arme1Damage, arme1);
				classementForce.put(arme2Damage, arme2);
				NavigableSet<Integer> armeDamage = (NavigableSet<Integer>) classementForce.keySet();
				Integer damage = armeDamage.floor(poindDeVieMonstre);
				if(damage == null) {
					return arme1.compareTo(arme2);
				}
			}
			
			return 0;
		}
	}
	
	//Changer new TreeSet<>(new Comparator<Homme>) par
	//new TreeSet<>((homme1, homme2) -> homme1.getForceDeVie() - homme2.getForceDeVie());
	public List<Homme> choixDuCamp(Bataille bataille){
		List<Homme> listHomme = new ArrayList<>();
		Monstre<? extends Pouvoir> monstreACombattre = bataille.getCampMonstres().selectionner();
		Map<Arme, TreeSet<Homme>> hommesArmes = new TreeMap<>(new ComparateurArmes(monstreACombattre));
		for(Homme homme : this.groupe) {
			TreeSet<Homme> ensembleHomme = new TreeSet<>((homme1, homme2) -> homme1.getForceDeVie() - homme2.getForceDeVie());
			if(hommesArmes.containsKey(homme.choisirArme(monstreACombattre))) {
				ensembleHomme = hommesArmes.get(homme.choisirArme(monstreACombattre));
				ensembleHomme.add(homme);
			}
			else {
				ensembleHomme.add(homme);
				hommesArmes.put(homme.choisirArme(monstreACombattre), ensembleHomme);
			}
		}
		for(Arme arme : hommesArmes.keySet()) {
			TreeSet<Homme> ensembleHomme = hommesArmes.get(arme);
			for(Homme homme : ensembleHomme) {
				if(listHomme.size() < 3) {
					listHomme.add(homme);
				}
			}
		}
		return listHomme;
	}
	
	public void supprimerHomme(Homme homme) {
		groupe.remove(homme);
	}
}

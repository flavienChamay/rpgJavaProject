package bataille;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import protagoniste.EtreVivant;

//TODO si Iterable alors devoir implémenter hasNext(), next() et remove()?????????
public class Camp<E extends EtreVivant> implements Iterable<E>{
	private List<E> liste = new LinkedList<>();

	/**
	 * If the Etre vivant is not in the list of the camp then it is added
	 * @param etre
	 */
	public void ajouter(E etre) {
		if(!liste.contains(etre)) {
			liste.add(etre);
		}
	}

	/**
	 * If the Etre Vivant is in the list and the list is not empty then it is removed
	 * @param etre
	 */
	public void eliminer(E etre) {
		if(liste.contains(etre) && (! liste.isEmpty())) {
			liste.remove(etre);
		}
	}

	@Override
	public String toString() {
		return liste.toString();
	}

	/**
	 * The iterator returns the iterator of a linkedList
	 * @return the iterator of the list
	 */
	@Override
	public Iterator<E> iterator() {
		return liste.iterator();
	}

	/**
	 * Return the number of warriors
	 * @return number of warriors
	 */
	public int nbCombattants() {
		return liste.size();
	}

	/**
	 * Return a random EtreVivant from the list
	 * @return
	 */
	public E selectionner() {
		Random randomGenerateur = new Random();
		int numeroCombattant = randomGenerateur.nextInt(liste.size());
		return liste.get(numeroCombattant);
	}

	public List<E> getListe() {
		return liste;
	}
	
	
}

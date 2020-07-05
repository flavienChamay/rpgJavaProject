package livre;

public interface Livre {
	public default void ecrire(String chaine) {
		System.out.println(chaine);
	}
}

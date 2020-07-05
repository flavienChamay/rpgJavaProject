package livre;

public class Ecran implements Livre{

	@Override
	public void ecrire(String chaine) {
		System.out.println(chaine);
	}

}

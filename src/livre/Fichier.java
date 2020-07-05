package livre;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Fichier implements Livre{

	@Override
	public void ecrire(String chaine) {
		try {
			String chemin= "./src/livre/histoire.txt";
			File writer = new File(chemin);
			try (FileWriter fichier = new FileWriter(writer, true);){
				fichier.write(chaine);
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("Le fichier n'a pas été trouvé ou n'est pas accessible.");
		} catch (IOException ioE) {
			System.out.println("Une exception concernant les entrées/sorties a été levée");
		}
	}

	//Decommenter pour utiliser ancienne version
//	@Override
//	public void ecrire(String texte) {
//		try {
//			String chemin= "./src/livre/histoire.txt";
//			File writer = new File(chemin);
//			FileWriter fichier = new FileWriter(writer, true);
//			try{
//				fichier.write(texte);
//			}finally {
//				fichier.close();
//				System.out.println("Fin des données à écrire!");
//			}
//		} catch (FileNotFoundException fnfe) {
//			System.out.println("Le fichier n'a pas été troué ou n'est pas accessible.");
//		} catch (IOException ioE) {
//			System.out.println("Une exception concernant les entrées/sorties a été levée");
//		}
//	}
	
	
	
}

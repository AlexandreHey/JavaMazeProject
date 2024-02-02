package maze;
/**
 * Classe héritant de la classe Exception. On lève une exception de ce type si le labyrinthe n'a pas le même nombre de cases à chaque lignes.
 * @author Alexandre
 *
 */
public class MazeReadingException extends Exception{
	
	private static final long serialVersionUID = 7384279056113542343L;
	String fileName ; 
	int lineException ; 
	String ErrorMessage ;
	
	public MazeReadingException(String fileName, int lineException, String ErrorMessage) {
		//Appel du constructeur de la classe mère
		super("Erreur de lecture du labyrinthe, dans le fichier " +fileName+" à la ligne "+lineException+". Erreur : "+ErrorMessage);
	}
}

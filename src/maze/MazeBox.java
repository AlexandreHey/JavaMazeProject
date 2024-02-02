package maze;
import graph.Vertex;

/**
 * Classe abstraite MazeBox qui représente une case du labyrinthe
 * @author Alexandre
 *
 */
public abstract class MazeBox implements Vertex{
	
	private int x; 
	private int y;
	private char label ; 
	private boolean crossable ;
	
	/**
	 * 
	 * @param x coordonée en abscisse de la case
	 * @param y coordonée en ordonnée de la case 
	 * @param label nom de la case : E pour Empty, W pour Wall, A pour Arrival, D pour Departure
	 * @param crossable boolean permettant de savoir si on peut passer sur la case ou non 
	 */
	public MazeBox(int x, int y, char label, boolean crossable) {
		this.x = x;
		this.y = y;
		this.label=label;
		this.crossable=crossable;
	}
	
	@Override
	public final char getLabel() {
		// TODO Auto-generated method stub
		return label;
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}
	public final boolean getCrossable() {
		return crossable ;
	}
}

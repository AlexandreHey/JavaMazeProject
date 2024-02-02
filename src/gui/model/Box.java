package gui.model;

import java.awt.*;

/**
 * Classe Box.
 * Un objet de cette classe est une box hexagonale qui a en valeurs les coordonées de son centre, son rayon (distance du centre à un sommet),
 * et son type (caractère 'E', 'W', 'A', 'D')
 * @author Alexandre
 *
 */
public class Box{
	private int x_center; 
	private int y_center;
	private int radius ; 
	private char type ;
	
	public Box(int x_center, int y_center, int radius,char type) {
		this.x_center=x_center;
		this.y_center=y_center;
		this.radius=radius;
		this.type=type;
		
	}
	
	
	
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type=type;
	}

	public int getY_center() {
		return y_center;
	}

	public int getRadius() {
		return radius;
	}

	public int getX_center() {
		return x_center;
	}

	/**
	 * Méthode qui renvoit une liste contenant les listes de coordonées en x et y des points d'un hexagone
	 * @return liste contenant les listes de coordonées en x et y des points d'un hexagone
	 */
	public int[][] coordinates(){
		int[] x = new int[6];
		int[] y = new int[6];
		for(int i=0;i<6;i++){
			double theta = (2*i+1)*Math.PI/6;
			x[i]=  (int) (x_center+radius*Math.cos(theta));
			y[i]=  (int) (y_center+radius*Math.sin(theta));
		}
		return new int[][] {x,y};
	}
	
	/**
	 * Méthode permattant de dessiner un hexagone en prenant les coordonées de ses 6 sommets grâce à la méthode précédente.
	 * @param g
	 * @param path, permet de savoir si le sommet qu on dessine fait parti des sommets du chemin le plus court. 
	 * Ces sommets là seront donc dessinés en vert 
	 */
	public void paint(Graphics g,boolean path){
		int[][] l = coordinates();
		switch(type) {
			case('A'): 
				g.setColor(Color.RED);
				break ;
			case('D'): 
				g.setColor(Color.BLUE);
				break;
			case('W'): 
				g.setColor(Color.GRAY);
				break;
			case('E'): 
				g.setColor(Color.WHITE);
				break;
		}
		//Si path est vraie alors le sommet fait parti du chemin le plus court donc on le dessine en vert 
		if(path) {
			g.setColor(new Color(50, 205, 50));
		}
		g.fillPolygon(l[0], l[1], 6);
		g.setColor(Color.BLACK);
		//On rajoute des bordures aux hexagones 
		((Graphics2D) g).setStroke(new BasicStroke(2));
		g.drawPolygon(l[0], l[1], 6); 	
	}
}

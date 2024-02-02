package gui.view;
import java.awt.Color;

import maze.MazeReadingException;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.Dimension;
import java.awt.Graphics;


/**
 * Classe MazePanel qui hérite de JPanel 
 * C'est dans ce panel que va être contenu le labyrinthe 
 * @author Alexandre
 *
 */
public class MazePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final MazeApp mazeApp ; 
	
	public MazePanel(MazeApp mazeApp){
		setMinimumSize(new Dimension(200, 200));
		setPreferredSize(new Dimension(849, 553));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		
		this.mazeApp = mazeApp ;
		
		//Ajout d'un listener pour récolter les données de la positions de la souris sur le panel 
		MazePanelMouseListener mazePanelMouseListener = new MazePanelMouseListener(mazeApp);
		addMouseListener(mazePanelMouseListener);
	}

	public MazeApp getMazeApp() {return mazeApp;}
	
	//Grâce à repaint() on appelle la méthode paintComponent qui va appeler à son tour la méthode PaintMaze de MazeAppModel qui va afficher 
	//le labyrinthe à l'écran
	public void notifyForUpdate() {
		// TODO Auto-generated method stub
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon bgImage = new ImageIcon("C:\\Users\\Alexandre\\Downloads\\1.5-Flow_s-HD-128px-_Eisregen_-Edit-for-_MCPatcher\\terrain\\sky0\\night.png");
	    g.drawImage(bgImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
		try {
			mazeApp.getMazeAppModel().PaintMaze(g);
		} catch (MazeReadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


package gui.view;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Classe WindowPanel qui hérite de JPanel
 * Ce panel est le plus grand conteneur, il contient trois panel. 
 * Un premier panel MazePanel qui est le panel qui va contenir le labyrinthe 
 * Un panel BoxPanel qui sera en bas de l'écran et dans lequel on va pouvoir modifier les cases du labyrinthe 
 * Un panel AnnexPanel qui sera à droite de l'écran et dans lequel on pourra sauvegarder un labyrinthe par exemple 
 * @author Alexandre
 *
 */
public class WindowPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MazePanel mazePanel ; 
	private BoxPanel boxPanel ; 
	private AnnexPanel annexPanel ; 
	
	public WindowPanel(MazeApp mazeApp) throws IOException {
		setLayout(new BorderLayout()) ;
		
	      add(mazePanel = new MazePanel(mazeApp), BorderLayout.CENTER) ;
	      add(boxPanel = new BoxPanel(mazeApp), BorderLayout.SOUTH) ;
	      add(annexPanel = new AnnexPanel(mazeApp), BorderLayout.EAST) ;
	}
	
	public BoxPanel getBoxPanel() {return boxPanel;}
	public MazePanel getMazePanel() {return mazePanel;}
	
	public void notifyForUpdate() {
		// TODO Auto-generated method stub
		mazePanel.notifyForUpdate();
		boxPanel.notifyForUpdate();
		annexPanel.notifyForUpdate();
	}

}

package gui.view;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.*;
import gui.model.*;

/**
 * Classe MazeApp qui hérite de JFrame.
 * Cette classe observe la classe MazeAppModel ; un changement dans MazeAppModel entraînera une modification dans MazeApp
 * Un objet de la classe MazeApp contient un panel WindowPanel
 * @author Alexandre
 *
 */
public class MazeApp extends JFrame implements ChangeListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7616313518053818425L;
	
	private final WindowPanel windowPanel ;
	private MazeAppModel mazeAppModel = new MazeAppModel();
	
	public MazeApp() throws IOException{
		super("Maze");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alexandre\\eclipse-workspace\\tp04\\data\\maze.jpg"));
				
		getContentPane().add(windowPanel = new WindowPanel(this));
		setContentPane(windowPanel);
		
		//On observe les modifications de MazeAppModel
		mazeAppModel.addObserver(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		setResizable(false);
		
		setVisible(true);
	}

	//Getters de MazeAppModel
	public MazeAppModel getMazeAppModel() {return mazeAppModel;}
	
	public WindowPanel getWindowPanel() {return windowPanel;}

	//Méthode de l'interface ChangeListener 
	//Principe de délégation, on renvoit les changements aux composants en dessous
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		windowPanel.notifyForUpdate();
	}

}

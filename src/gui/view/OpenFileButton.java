package gui.view;

import java.awt.event.*;

import javax.swing.JButton;

import gui.model.MazeAppModel;

/**
 * Classe OpenFileButton qui est un bouton qui permet d'ouvrir un fichier texte et d'afficher le labyrinthe correspondant
 * @author Alexandre
 *
 */
public class OpenFileButton extends JButton implements ActionListener{
/**
* 
*/
	private static final long serialVersionUID = 1L;
	
	private final MazeApp mazeApp ; 
	
	public OpenFileButton(MazeApp mazeApp) {
		super("Open file");
		this.mazeApp=mazeApp ;
		
		addActionListener(this);
	}

	public void notifyForUpdate() {
		// TODO Auto-generated method stub
		MazeAppModel mazeAppModel = mazeApp.getMazeAppModel();
		if(mazeAppModel.isModified()) {
			repaint();
		}
	}

	//Principe de délégation
	@Override
	public void actionPerformed(ActionEvent e) {
		mazeApp.getMazeAppModel().openFile();
		
	}
}

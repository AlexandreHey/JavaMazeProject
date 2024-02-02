package gui.view;

import java.awt.event.*;

import java.io.IOException;

import javax.swing.JButton;

import gui.model.MazeAppModel;

/**
 * Bouton qui permet de sauvegarder le labyrinthe dans un fichier texte sur l'oridnateur 
 * @author Alexandre
 *
 */
public class SaveButton extends JButton implements ActionListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final MazeApp mazeApp ; 
	
	public SaveButton(MazeApp mazeApp) {
		super("Save");
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

	//Principe de délégation, on renvoit à une méthode de MazeAppModel
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			mazeApp.getMazeAppModel().save();
		} catch (IOException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
		
	}

}

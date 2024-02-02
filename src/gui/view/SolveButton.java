package gui.view;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JButton;

import gui.model.MazeAppModel;

/**
 * Classe SolveButton qui est un bouton permettant de résoudre le labyrinthe 
 * @author Alexandre
 *
 */
public class SolveButton extends JButton implements ActionListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final MazeApp mazeApp ; 
	
	public SolveButton(MazeApp mazeApp) {
		super("Solve");
		this.mazeApp=mazeApp ;
		setBackground(new Color(50, 205, 50));
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
		mazeApp.getMazeAppModel().solve();
	}

}

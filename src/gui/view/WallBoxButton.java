package gui.view;
import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

import gui.model.MazeAppModel;

/**
 * Classe WallBoxButton qui permet de séléctionner un mur et de le mettre sur le labyrinthe
 * @author Alexandre
 *
 */
public class WallBoxButton extends JButton implements ActionListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final MazeApp mazeApp ; 
	
	public WallBoxButton(MazeApp mazeApp) {
		super("Wall Box");
		this.mazeApp=mazeApp ;
		setBackground(Color.GRAY);
		addActionListener(this);
	}

	public void notifyForUpdate() {
		// TODO Auto-generated method stub
		MazeAppModel mazeAppModel = mazeApp.getMazeAppModel();
		if(mazeAppModel.isModified()) {
			repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		mazeApp.getMazeAppModel().setIsClicked(true);
		mazeApp.getMazeAppModel().setEditBoxLabel('W');
	}

}

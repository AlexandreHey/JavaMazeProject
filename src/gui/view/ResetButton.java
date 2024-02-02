package gui.view;

import java.awt.event.*;


import javax.swing.JButton;

/**
 * Bouton qui permet de remettre à zéro le labyrinthe 
 * @author Alexandre
 *
 */
public class ResetButton extends JButton implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final MazeApp mazeApp ; 
	
	public ResetButton(MazeApp mazeApp) {
		super("Reset");
		this.mazeApp=mazeApp ;
		
		addActionListener(this);
	}

	public void notifyForUpdate() {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int height = mazeApp.getMazeAppModel().getDimensionMaze()[0];
		int width = mazeApp.getMazeAppModel().getDimensionMaze()[1];
		mazeApp.getMazeAppModel().resetMaze(height, width);
	}

}

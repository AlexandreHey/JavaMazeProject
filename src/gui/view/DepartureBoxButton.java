package gui.view;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

import gui.model.*;

/**
 *  Classe DepartureBoxButton qui permet de séléctionner une boîte de départ et de la mettre sur le labyrinthe
 * @author Alexandre
 *
 */
public class DepartureBoxButton extends JButton implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final MazeApp mazeApp ; 
	
	public DepartureBoxButton(MazeApp mazeApp){
		super("Departure Box");
		this.mazeApp=mazeApp ;
		setBackground(Color.BLUE);
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
		mazeApp.getMazeAppModel().setEditBoxLabel('D');
	}

}

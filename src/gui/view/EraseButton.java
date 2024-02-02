package gui.view;

import java.awt.event.*;

import javax.swing.JButton;

import gui.model.MazeAppModel;

/**
 *  Classe EraseButton qui permet d'effacer les prochaines cases sur lequelles on clique 
 * @author Alexandre
 *
 */
public class EraseButton extends JButton implements ActionListener{
/**
* 
* */
private static final long serialVersionUID = 1L;
	
private final MazeApp mazeApp ; 
	
	public EraseButton(MazeApp mazeApp) {
		super("Erase");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		mazeApp.getMazeAppModel().setIsClicked(true);
		mazeApp.getMazeAppModel().setEditBoxLabel('E');
	}

}

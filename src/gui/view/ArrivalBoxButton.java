package gui.view;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import gui.model.MazeAppModel;

/**
 * Classe ArrivalBoxButton qui permet de séléctionner une boîte d'arrivée et de la mettre sur le labyrinthe
 * @author Alexandre
 *
 */
public class ArrivalBoxButton extends JButton implements ActionListener {
/**
* 
* */
private static final long serialVersionUID = 1L;
	
private final MazeApp mazeApp ; 
	
	public ArrivalBoxButton(MazeApp mazeApp) {
		super("Arrival Box");
		this.mazeApp=mazeApp ;
		setBackground(Color.RED);
		addActionListener(this);
		
	}

	//Le booléen isModified est vrai que lorsque le programme se lance pour la première fois 
	public void notifyForUpdate() {
		// TODO Auto-generated method stub
		MazeAppModel mazeAppModel = mazeApp.getMazeAppModel();
		if(mazeAppModel.isModified()) {
			repaint();
		}
	}
	
	//Méthode de l'interface ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		mazeApp.getMazeAppModel().setIsClicked(true);
		mazeApp.getMazeAppModel().setEditBoxLabel('A');
	}

}

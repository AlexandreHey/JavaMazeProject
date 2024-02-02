package gui.view;

import java.awt.BorderLayout;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

import gui.model.MazeAppModel;

/**
 * Classe HelpButton qui fait apparaître une zone de texte contenant les méthodes d'utilisation du labyrinthe 
 * @author Alexandre
 *
 */
public class HelpButton extends JButton implements ActionListener{
/**
* 
*/
private static final long serialVersionUID = 1L;
	
private final MazeApp mazeApp ; 
	
	public HelpButton(MazeApp mazeApp) {
		super("Help");
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
		JFrame helpFrame = new JFrame("Help");
		helpFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alexandre\\eclipse-workspace\\tp04\\data\\askingPoint.jpg"));
		helpFrame.setBounds(new Rectangle(500, 125, 940, 350));
		
		
		JTextArea textArea = new JTextArea();
		textArea.setText("1) Pour résoudre le labyrinthe, appuyez sur la touche \"Solve\". Le chemin qui s'affiche en vert est le plus court chemin de la case départ bleue à la case d'arrivée rouge.\r\n\r\n2) Pour changer les cases de départ et d'arrivée, cliquez une fois sur le bouton \"DepartureBox\" ou \"ArrivalBox\" puis séléctionnez la case souhaitée.\r\n\r\n3) Pour changer les murs, cliquez une fois sur la case \"WallBox\" puis, sans re-cliquer sur cette case, séléctionnez autant de mur du labyrinthe que souhaité.\r\n\r\n4) Pour effacer une case, cliquez une fois sur la case \"Erase\" puis cliquez sur la case souhaitée.\r\n\r\n5) Pour tout effacer sur le labyrinthe en cours, cliquez sur le bouton \"Reset\".\r\n\r\n6) Pour créer un nouveau labyrinthe vide de taille quelconque, renseignez la longueur souhaitée dans la case \"Height\" et la largeur dans la case \"Width\". Cliquez ensuite sur \"Ok\".\r\n\r\n7) Pour sauvegarder le labyrinthe, cliquez sur le bouton \"Save\" puis séléctionnez l'emplacement désiré. \r\n\r\n8) Pour charger un labyrinthe déjà sauvegardé, cliquez sur le bouton \"Open file\" puis séléctionnez le fichier souhaité.\r\n\r\n\r\n                                                        Amusez-vous bien !! ");
		helpFrame.getContentPane().add(textArea, BorderLayout.CENTER);
		helpFrame.setVisible(true);
		
	}
}

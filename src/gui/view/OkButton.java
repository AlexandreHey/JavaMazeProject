package gui.view;
import javax.swing.*;
import java.awt.event.*;

/**
 * Bouton "Ok" qui permet de récolter les données des dimensions du labyrinthe entrées par l'utilisateur 
 * @author Alexandre
 *
 */
public class OkButton extends JButton implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MazeApp mazeApp;
	
	
	public OkButton(MazeApp mazeApp) {
		super("Ok");
		this.mazeApp=mazeApp ;
		
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str_height = mazeApp.getWindowPanel().getBoxPanel().getHeightTextField().getText();
		String str_width = mazeApp.getWindowPanel().getBoxPanel().getWidthTextField().getText();
		int height = Integer.parseInt(str_height);
		int width = Integer.parseInt(str_width);
		try {
			if(height<2 || height>35 || width<2 || width>48) {
				throw new MazeSizeException();
			}
			mazeApp.getMazeAppModel().resetMaze(height, width);
		}
		catch(MazeSizeException mazeSizeException) {
		}
	}

}

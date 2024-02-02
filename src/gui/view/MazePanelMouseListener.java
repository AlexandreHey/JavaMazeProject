package gui.view;
import java.awt.event.*;

/**
 * Classe MazePanelMouseListener qui permet de détécter les positions de la sourie sur le panel MazePanel
 * @author Alexandre
 *
 */
public class MazePanelMouseListener extends MouseAdapter implements MouseListener,MouseMotionListener {

	private MazeApp mazeApp;
	
	public MazePanelMouseListener(MazeApp mazeApp) {
		// TODO Auto-generated constructor stub
		this.mazeApp=mazeApp;
	}

	//Dans le cas où on veut placer des cases de départ ou d'arrivée on clique une fois et il faut re-cliquer sur le bouton Departure ou 
	//Arrival si on veut ppouvoir éditer une nouvelle fois 
	@Override
	public final void mouseClicked(MouseEvent e) {
		if(mazeApp.getMazeAppModel().getIsClicked()) {
			mazeApp.getMazeAppModel().setSelectedBox(e.getX(),e.getY());
			
			char editBoxLabel = mazeApp.getMazeAppModel().getEditBoxLabel();
			mazeApp.getMazeAppModel().editbox(editBoxLabel);
			
			if(editBoxLabel=='A' || editBoxLabel=='D') {
				mazeApp.getMazeAppModel().setIsClicked(false);
			}
		}
	}
	
	//Dans le cas où on veut effacer une case ou placer un mur on peut cliquer plusieurs fois à l'écran sans avoir à re-cliquer sans cesse 
	//sur le bouton WallBox ou Erase
	@Override
	public final void mouseDragged(MouseEvent e) {
		mazeApp.getMazeAppModel().setSelectedBox(e.getX(),e.getY());
		char editBoxLabel = mazeApp.getMazeAppModel().getEditBoxLabel();
		if(editBoxLabel=='W' || editBoxLabel=='E') {
			mazeApp.getMazeAppModel().editbox(editBoxLabel);
		}
		mazeApp.getMazeAppModel().setIsClicked(false);
	}
}

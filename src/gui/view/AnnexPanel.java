package gui.view;

import javax.swing.*;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

/**
 * Classe AnnexPanel qui est un JPanel qui contient les boutons save, solve, open, help et reset
 * @author Alexandre
 *
 */
public class AnnexPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final SaveButton save ; 
	private final ResetButton reset ; 
	private final SolveButton solve ;
	private final OpenFileButton open ;
	private final HelpButton help ;

	public AnnexPanel(MazeApp mazeApp){
		setBackground(Color.BLACK);
		
		add(save = new SaveButton(mazeApp));
		add(reset = new ResetButton(mazeApp));
		add(solve = new SolveButton(mazeApp));
		add(open = new OpenFileButton(mazeApp));
		add(help = new HelpButton(mazeApp));
		
		open.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		open.setActionCommand("Open file");
		open.setPreferredSize(new Dimension(55, 21));
		open.setMinimumSize(new Dimension(55, 21));
		open.setMaximumSize(new Dimension(55, 21));
		
		//Code généré automatiquement par Eclipse grâce à la fonctionnalitée "Open with WindowBuilder"
		//Permet de mettre tous les composants aux bons endroits et ajuster leur taille 
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(solve, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(58)
							.addComponent(reset, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(save, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(27, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addComponent(open, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
					.addGap(48))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(130, Short.MAX_VALUE)
					.addComponent(help))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(help)
					.addGap(9)
					.addComponent(solve, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(84)
					.addComponent(save, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(open, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
					.addComponent(reset, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(46))
		);
		setLayout(groupLayout);

	}

	//Principe de délégation, on renvoit les changements aux composants en dessous
	public void notifyForUpdate() {
		// TODO Auto-generated method stub
		save.notifyForUpdate();
		reset.notifyForUpdate();
		solve.notifyForUpdate();
	}
}

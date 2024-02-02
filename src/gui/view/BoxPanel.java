package gui.view;

import java.awt.*;


import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Classe BoxPanel qui étends JPanel et qui cotneitnles boutons permettant de laisser à l'utilisateur de choisir la taille du labyrinthe 
 * et d'éditer le labyrinthe comme il le souhaite 
 * @author Alexandre
 *
 */
public class BoxPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final DepartureBoxButton departureBoxGui ;
	private final ArrivalBoxButton arrivalBoxGui ; 
	private final WallBoxButton wallBoxGui ; 
	private final EraseButton erase ;
	private OkButton okButton;
	private JTextField heightTextField;
	private JTextField widthTextField;
	private MazeApp mazeApp ;
	
	public BoxPanel(MazeApp mazeApp){
		this.mazeApp=mazeApp;
		setPreferredSize(new Dimension(602, 189));
		setMinimumSize(new Dimension(200, 200));
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setRequestFocusEnabled(false);
		setBounds(new Rectangle(1, 200, 200, 200));
		setBackground(Color.BLACK);
		
		add(departureBoxGui = new DepartureBoxButton(mazeApp));
		add(arrivalBoxGui = new ArrivalBoxButton(mazeApp));
		add(wallBoxGui = new WallBoxButton(mazeApp));
		add(erase = new EraseButton(mazeApp));
		add(okButton = new OkButton(mazeApp));
		okButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		heightTextField = new JTextField();
		heightTextField.setName("Height\r\n");
		heightTextField.setColumns(10);
		JLabel heightLabel = new JLabel("Height");
		JLabel lblNewLabel_1 = new JLabel("(min. 2 - max. 48)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		heightLabel.setForeground(Color.WHITE);
		lblNewLabel_1.setForeground(Color.WHITE);
		
		widthTextField = new JTextField();
		widthTextField.setName("Width\r\n");
		widthTextField.setColumns(10);
		JLabel widthLabel = new JLabel("Width\r\n");
		JLabel lblNewLabel = new JLabel("(min. 2 - max. 35)\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		widthLabel.setForeground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		
		
		//Réglage de tous les paramètres pour mettre en place les différents composants. Le code à été réalisé automatiquement grâce 
		//à "Open with Window Builder" sur Eclipse
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(widthLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
								.addComponent(heightLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(heightTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(widthTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(57)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(departureBoxGui, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(arrivalBoxGui, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(wallBoxGui, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
							.addGap(74)
							.addComponent(erase, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
					.addGap(437))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(departureBoxGui, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(arrivalBoxGui, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addComponent(erase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(wallBoxGui, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(heightLabel)
								.addComponent(heightTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(1)
							.addComponent(lblNewLabel)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(widthLabel)
								.addComponent(widthTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(okButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	
	
	public JTextField getHeightTextField() {return heightTextField;}
	public JTextField getWidthTextField() {return widthTextField;}

	public MazeApp getMazeApp() {return mazeApp;}

	//Principe de délégation, on renvoit les changements aux composants en dessous
	public void notifyForUpdate() {
		// TODO Auto-generated method stub
		departureBoxGui.notifyForUpdate();
		arrivalBoxGui.notifyForUpdate();
		wallBoxGui.notifyForUpdate();
		erase.notifyForUpdate();
	}
}

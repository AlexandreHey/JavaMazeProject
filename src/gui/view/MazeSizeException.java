package gui.view;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.*;

/**
 * Classe d'Exception personnalisée dans le cas où l'utilisateur ne respecte ps les dimensions minimales ou maximales du labyrinthe 
 * On affiche alors une zone de texte avec le message d'erreur
 * @author Alexandre
 *
 */
public class MazeSizeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MazeSizeException() {
		//Appel du constructeur de la classe mère
		super();
		JFrame sizeExceptionFrame = new JFrame("Maze size exception !");
		sizeExceptionFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alexandre\\eclipse-workspace\\tp04\\data\\warning.jpg"));
		JTextArea textArea = new JTextArea();
		textArea.setText("Warning : you have not respected the minimum/maximum dimensions !");
		sizeExceptionFrame.setBounds(new Rectangle(400, 160, 400, 60));
		sizeExceptionFrame.getContentPane().add(textArea, BorderLayout.CENTER);
		sizeExceptionFrame.setVisible(true);
	}
}

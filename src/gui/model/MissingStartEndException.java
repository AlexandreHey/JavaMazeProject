package gui.model;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Classe qui hérite de la classe Exception et qui lève une exception si il manque une box d'arrivée ou de départ dans le labyrinthe.
 * @author Alexandre
 *
 */
public class MissingStartEndException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String missingBox ;
	
	public MissingStartEndException(char missingDepartureArrivalBox) {
		//Appel du constructeur de la classe mère
		super();
		if(missingDepartureArrivalBox=='A') {missingBox="arrival";}
		if(missingDepartureArrivalBox=='D') {missingBox="departure";}
		JFrame sizeExceptionFrame = new JFrame("Missing "+missingBox+" box !");
		sizeExceptionFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alexandre\\eclipse-workspace\\tp04\\data\\warning.jpg"));
		JTextArea textArea = new JTextArea();
		if(missingBox=="arrival") {
			textArea.setText("Warning : you have to select an "+missingBox+" box !");
		}
		else {
			textArea.setText("Warning : you have to select a "+missingBox+" box !");
		}
		sizeExceptionFrame.setBounds(new Rectangle(400, 160, 300, 60));
		sizeExceptionFrame.getContentPane().add(textArea, BorderLayout.CENTER);
		sizeExceptionFrame.setVisible(true);
	}

}

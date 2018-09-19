import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Programm to print.
 * Color, size and doublesided can be chosen
 * 
 * @author Daniela S, Joel A
 * @version 1.0
 * @since 12.09.2018
 *
 */

import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import org.junit.Test;

public class Printer {
	
	/**
	 * 
	 * @param Coloration colour or black an white
	 * @param Doublesided doublesided or not
	 * @param Size size of the print
	 * @param Printer which print is selected
	 */
	public static void preparePrint(String Coloration, String Doublesided, String Size, String Printer) {
		
		boolean proceed;
		
		//check if Coloration and/or Doublesided is empty and inserts the result in proceed
		proceed = checkStringEmptiness(Coloration);
		if (proceed == false) {
		} else {
			proceed = checkStringEmptiness(Doublesided);
		}
		
		//proceed == true -> both string aren't empty
		if (proceed == true) {
			//text for sending print
			StringBuilder submitText = new StringBuilder(
					"Print job for " + Printer + ":\n"
				  + Coloration + "\n"
				  + Doublesided + "\n"
				  + Size + "\n\n"
				  + "Sending print job, please hold.."
			);
			
			//popup message box that displays submitText (that the print job is being sent)
			final JOptionPane OptionPane = new JOptionPane(submitText, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
			final JDialog PrintMessage = new JDialog();

			PrintMessage.setTitle("Message");
			PrintMessage.setModal(true);
			
			PrintMessage.setContentPane(OptionPane);
			
			PrintMessage.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			PrintMessage.setResizable(false);
			PrintMessage.pack();
			
			//timer for delay before closing message box, after 2.5 sec it closes automatically
			Timer timer = new Timer(2500, new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					PrintMessage.dispose();
				}
			});
			
			timer.setRepeats(false);
			
			timer.start();
			
			PrintMessage.setVisible(true);
			
			//switch to move forward with selected printer, selecting its function
			switch(Printer) {
			case "Printer Appletree":
				PrinterAppletree.printerAppletree();
				break;
			case "Printer Pinetree":
				PrinterPinetree.printerPinetree();
				break;
			case "Printer Palmtree":
				PrinterPalmtree.printerPalmtree();
				break;
			}
			
		//proceed == false -> atleast one of both strings is empty
		} else if (proceed == false) {
			//popup message box explaining that there's missing information, not proceeding
			//user has the chance of sending another print job (with hopefully all needed informations)
			JOptionPane.showMessageDialog(Main.frame, "Couldn't send print job:\n- missing information. \n \nPlease make sure everything \nis selected and try again.");
		}
		
	}
	
	/**
	 * checks if the String is emtpy
	 * 
	 * @param CheckedString String that needs to be checked
	 * @return returns wheter it is empty (false) or not empty (true)
	 */
	private static boolean checkStringEmptiness(String CheckedString) {
		
		boolean empty = false;
		
		if (CheckedString == null) {
			empty = false;
		} else {
			empty = true;
		}
		
		return empty;
		
	}
	
}

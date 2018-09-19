import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Programm to print.
 * Color, size and doublesided can be chosen
 * 
 * @author Daniela S, Joel A
 * @version 1.0
 * @since 12.09.2018
 *
 */

public class PerformPrint {

	/**
	 * function with the functionallity to perform the actual print
	 * 
	 * @param Printername which printer is currently printing
	 */
	public static void performPrint(String Printername) {
		
		//message saying the print has started
		String PrintStarting = "Printer " + Printername + " has received\n"
					   + "the print job. Printing has started.\n"
					   + "This might take a while..";
		
		final JOptionPane OptionPane = new JOptionPane(PrintStarting, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
		final JDialog PrintMessage = new JDialog();

		PrintMessage.setTitle("Message");
		PrintMessage.setModal(true);
		
		PrintMessage.setContentPane(OptionPane);
		
		PrintMessage.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		PrintMessage.setResizable(false);
		PrintMessage.pack();
		
		//timer, delay with 5 sec duration during print
		Timer timer = new Timer(5000, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrintMessage.dispose();
			}
		});
		
		timer.setRepeats(false);
		
		timer.start();
		
		PrintMessage.setVisible(true);
		
		//after the timer stopped, succed popup message box appears stating the print has finished
		String FinishPrinting = "Printer " + Printername + " has \nfinished printing!";
		
		JOptionPane.showMessageDialog(Main.frame, FinishPrinting);
		
		//everything is being deselected
		Main.rbBlackWhite.setSelected(false);
		Main.rbColor.setSelected(false);
		Main.rbNo.setSelected(false);
		Main.rbYes.setSelected(false);
		
	}
	
}

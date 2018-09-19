import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import org.junit.Test;

/**
 * Programm to print.
 * Color, size and doublesided can be chosen
 * 
 * @author Daniela S, Joel A
 * @version 1.0
 * @since 12.09.2018
 *
 */

public class Main {
	
	static JFrame frame = new JFrame();
	static JRadioButton rbBlackWhite;
	static JRadioButton rbColor;
	static JRadioButton rbYes;
	static JRadioButton rbNo;
	static JComboBox<String> cbSizes;
	static JComboBox<String> cbPrinter;
	
	//creating the gui
	Main() {
		
		frame.setSize(220, 365);	
		frame.setLayout(new FlowLayout());
		frame.setResizable(false);
		
		rbColor();
		rbDoublesided();
		cbSize();
		cbPrinter();
		sendButton();

		frame.setVisible(true);
	}
	
	//radio buttons for with color or black and white
	private void rbColor() {
		
		JLabel lColoration = new JLabel("Coloration:");
		lColoration.setFont(new Font("Sans Serif", Font.PLAIN, 25));
		frame.add(lColoration);
		
		rbBlackWhite = new JRadioButton("black and white");
		rbColor = new JRadioButton("color");
		rbBlackWhite.setFont(new Font("Sans serif", Font.PLAIN, 15));
		rbColor.setFont(new Font("Sans serif", Font.PLAIN, 15));
		
		ButtonGroup colorGroup = new ButtonGroup();
		colorGroup.add(rbBlackWhite);
		colorGroup.add(rbColor);
		
		frame.add(rbBlackWhite);
		frame.add(rbColor);
		
	}
	
	//radio buttons for doublesided or not
	private void rbDoublesided () {
		
		JLabel lDoublesided = new JLabel("Doublesided?:");
		lDoublesided.setFont(new Font("Sans Serif", Font.PLAIN, 25));
		frame.add(lDoublesided);
		
		rbYes = new JRadioButton("Yes");
		rbNo = new JRadioButton("No");
		rbYes.setFont(new Font("Sans serif", Font.PLAIN, 15));
		rbNo.setFont(new Font("Sans serif", Font.PLAIN, 15));
		
		ButtonGroup doublesidedGroup = new ButtonGroup();
		doublesidedGroup.add(rbYes);
		doublesidedGroup.add(rbNo);
		
		frame.add(rbYes);
		frame.add(rbNo);
		
	}
	
	//combo box with all available paper sizes
	private void cbSize () {
		
		JLabel lSize = new JLabel("Papersize:");
		lSize.setFont(new Font("Sans Serif", Font.PLAIN, 25));
		frame.add(lSize);
		
		String[] sizes = new String[] {
			"A5 - 148mm x 210mm", "A4 - 210mm x 297mm", "A3 - 297mm x 420mm", "A2 - 420mm x 594mm"	
		};
		
		cbSizes = new JComboBox<String>(sizes);
		cbSizes.setVisible(true);
		frame.add(cbSizes);
		
	}

	//combo box with all available printers
	private void cbPrinter () {
		
		JLabel lPrinter = new JLabel("Printer:");
		lPrinter.setFont(new Font("Sans Serif", Font.PLAIN, 25));
		frame.add(lPrinter);
		
		String[] printers = new String[] {
			"Printer Appletree", "Printer Pinetree", "Printer Palmtree"	
		};
		
		cbPrinter = new JComboBox<String>(printers);
		cbPrinter.setVisible(true);
		frame.add(cbPrinter);
		
	}
	
	//button to send the print job
	private void sendButton() {
		
		JButton button = new JButton("Print");
		button.setFont(new Font("Sans Serif", Font.PLAIN, 25));
		
		button.addActionListener(new ActionListener() {
			
			//if button pressed
			public void actionPerformed(ActionEvent e) {
				
				String infoColoration = null;
				
				//checking which one was selected and applying that to the string
				if (rbBlackWhite.isSelected()) {
					infoColoration = "Black and white,";
				} else if (rbColor.isSelected()) {
					infoColoration = "With color,";
				}
				
				String infoDoublesided = null;
				
				//checking which one was selected and applying that to the string
				if (rbYes.isSelected()) {
					infoDoublesided = "doublesided,";
				} else if (rbNo.isSelected()) {
					infoDoublesided = "onesided,";
				}
				
				//inserting selected item in string
				String infoSize = (String) cbSizes.getSelectedItem();
				//inserting selected item in string
				String infoPrinter = (String) cbPrinter.getSelectedItem();
				
				//calling function preparePrint
				Printer.preparePrint(infoColoration, infoDoublesided, infoSize, infoPrinter);
				
			}
			
		});
		
		frame.add(button);
		
	}
	
	public static void main(String[] args) {
		
		Main go = new Main();
		
	}

	

}
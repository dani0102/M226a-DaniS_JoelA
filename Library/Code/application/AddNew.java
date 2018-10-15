package application;

import add.AddNewLoan;
import add.AddNewMedia;
import add.AddNewReturn;
import javafx.scene.control.Hyperlink;

//Alle hyperlink für data manipulation

public class AddNew {

	public static Hyperlink addLoan = new Hyperlink("Loan media");
	public static Hyperlink addMedia = new Hyperlink("Add new media");
	public static Hyperlink addReturn = new Hyperlink("Return media");
	
	public static void addOptions(String table, String name) {
		
		addLoan.setOnAction(e -> {
			AddNewLoan.add(table, name);
		});
		
		addMedia.setOnAction(e -> {
			AddNewMedia.add(table, name);
		});
		
		addReturn.setOnAction(e -> {
			AddNewReturn.add(table, name);
		});
		
	}
	
}

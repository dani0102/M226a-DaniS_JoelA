package add;

import application.AlertBox;
import application.Database;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import tables.*;

//Funktion um Medium auszuleihen
 
public class AddNewLoan {

	static Scene scene;
	//titleoptions = alle verfügbaren titel
	public static ObservableList<String> titleoptions = FXCollections.observableArrayList();
	
	//table = letzte tabelle, name = eingeloggter user
	public static void add(String table, String name) {
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label lusername = new Label("Username:");
		GridPane.setConstraints(lusername, 1, 2);
		
		//eingeloggter user wird angezeigt, nicht veränderbar
		TextField username = new TextField();
		username.setText(name);
		username.setEditable(false);
		GridPane.setConstraints(username, 2, 2);
		
		ComboBox<String> cbtitle = new ComboBox();
		
		Label lsort = new Label("Sort:");
		GridPane.setConstraints(lsort, 1, 3);
		
		ObservableList<String> sortoptions = FXCollections.observableArrayList(
				"Book", "DVD"
			);
			
		ComboBox<String> cbsort = new ComboBox(sortoptions);
		cbsort.setOnAction(e -> {
			
			//wenn Medium augewählt wird, werden alle passenden Titel 
			//rausgesucht und in cbtitle bzw titleoptions eingefügt
			titleoptions.clear();
			Database.selectAllLoanable(cbsort.getValue());
			cbtitle.setItems(titleoptions);
			
		});
		GridPane.setConstraints(cbsort, 2, 3);
		
		Label ltitle = new Label("Title:");
		GridPane.setConstraints(ltitle, 1, 4);
		
		GridPane.setConstraints(cbtitle, 2, 4);
		
		Button bcreateLoan = new Button("Loan");
		bcreateLoan.setOnAction(e -> {
			
			//kontrolliren ob alle felder ausgefühlt sind
			if(cbsort.getValue() != null && cbtitle.getValue() != null) {
								
				//funktion mit Datenbank wird ausgeführt, gibt false / true zurück
				boolean result = Database.addLoan(Database.userID(name), Database.mediaID(cbtitle.getValue()));
				
				//funktion erfolgreich durchgeführt
				if(result == true) {
					AlertBox.alertBox("Media has successfully been loaned.");
					
					//zurück zur letzten Tabelle
					switch (table) {
					case "All Books":
						TableAllBooks.table(name);
						break;
					case "All DVD's":
						TableAllDVD.table(name);
						break;
					case "All free medias":
						TableAllFree.table(name);
						break;
					case "All loaned medias":
						TableAllLoaned.table(name);
						break;
					case "All medias":
						TableAllMedias.table(name);
						break;
					}
					
				//funktion gibt fehler zurück
				} else if (result == false) {
					AlertBox.alertBox("There has been an error.\nPlease try again");
					add(table, name);
				}
				
			//nicht alle felder wurden ausgefühlt
			} else {
				AlertBox.alertBox("There has been an error.\nPlease try again and make sure\neverything is filled out.");
				add(table, name);
			}
			
			
		});
		
		//abbrechen
		Button bcancel = new Button("Cancel");
		bcancel.setOnAction(e -> {
			
			//zurück zur letzten Tabelle
			switch (table) {
			case "All Books":
				TableAllBooks.table(name);
				break;
			case "All DVD's":
				TableAllDVD.table(name);
				break;
			case "All free medias":
				TableAllFree.table(name);
				break;
			case "All loaned medias":
				TableAllLoaned.table(name);
				break;
			case "All medias":
				TableAllMedias.table(name);
				break;
			}
			
		});

		HBox hbox = new HBox();
		hbox.setSpacing(56);
		hbox.getChildren().addAll(bcreateLoan, bcancel);
		GridPane.setConstraints(hbox, 2, 5);
		
		grid.getChildren().addAll(lusername, username, lsort, cbsort, ltitle, cbtitle, hbox);
		
		scene = new Scene(grid, 300, 180);
		
		Main.window.setScene(scene);
		
	}
	
}

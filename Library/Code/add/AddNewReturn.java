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
import tables.TableAllBooks;
import tables.TableAllDVD;
import tables.TableAllFree;
import tables.TableAllLoaned;
import tables.TableAllMedias;

//Funktion um Medium zurückzubringen

public class AddNewReturn {

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
		
		Label ltitle = new Label("All your loaned \nmedias:");
		GridPane.setConstraints(ltitle, 1, 3);
		
		titleoptions.clear();
		Database.selectAllLoanedByUser(Database.userID(name));
		
		ComboBox<String> cbtitle = new ComboBox();
		cbtitle.setItems(titleoptions);
		GridPane.setConstraints(cbtitle, 2, 3);
		
		Button breturnItem = new Button("Return");
		breturnItem.setOnAction(e -> {
						
			//kontrolliren ob alle felder ausgefühlt sind
			if(cbtitle.getValue() != null) {
				
				//funktion mit Datenbank wird ausgeführt, gibt false / true zurück
				boolean result = Database.addReturn(Database.userID(name), Database.mediaID(cbtitle.getValue()));
				
				//funktion erfolgreich durchgeführt
				if(result == true) {
					AlertBox.alertBox("Media has successfully been returned.");
					
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
		hbox.setSpacing(50);
		hbox.getChildren().addAll(breturnItem, bcancel);
		GridPane.setConstraints(hbox, 2, 4);
		
		grid.getChildren().addAll(lusername, username, ltitle, cbtitle, hbox);
		
		scene = new Scene(grid, 300, 150);
		
		Main.window.setScene(scene);
		
	}
	
}

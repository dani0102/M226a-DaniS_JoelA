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

//Funktion um Medium hinzuzufügen

public class AddNewMedia {

	static Scene scene;
	
	//table = letzte tabelle, name = eingeloggter user
	public static void add(String table, String name) {
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label ltitle = new Label("Title:");
		GridPane.setConstraints(ltitle, 1, 2);
		
		TextField tftitle = new TextField();
		tftitle.setEditable(true);
		GridPane.setConstraints(tftitle, 2, 2);

		Label lauthor = new Label("Author:");
		GridPane.setConstraints(lauthor, 1, 3);
		
		TextField tfauthor = new TextField();
		tfauthor.setEditable(true);
		GridPane.setConstraints(tfauthor, 2, 3);
		
		Label lsort = new Label("Sort:");
		GridPane.setConstraints(lsort, 1, 4);
		
		ObservableList<String> sortoptions = FXCollections.observableArrayList(
				"Book", "DVD"
			);
			
		ComboBox<String> cbsort = new ComboBox(sortoptions);
		GridPane.setConstraints(cbsort, 2, 4);

		Database.selectAllLoanedByUser(Database.userID(name));
		
		Button bnewMedia = new Button("Add");
		bnewMedia.setOnAction(e -> {
								
			//kontrolliren ob alle felder ausgefühlt sind
			if(tftitle.getText() != null && tfauthor.getText() != null && cbsort.getValue() != null) {
				
				//funktion mit Datenbank wird ausgeführt, gibt false / true zurück
				boolean result = Database.addMedia(tftitle.getText(), tfauthor.getText(), Database.sortID(cbsort.getValue()));
				
				//funktion erfolgreich durchgeführt
				if(result == true) {
					AlertBox.alertBox("Media has successfully been added.");
					
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
		hbox.setSpacing(70);
		hbox.getChildren().addAll(bnewMedia, bcancel);
		GridPane.setConstraints(hbox, 2, 5);

		grid.getChildren().addAll(ltitle, tftitle, lauthor, tfauthor, lsort, cbsort, hbox);
		
		scene = new Scene(grid, 300, 180);
		
		Main.window.setScene(scene);
		
	}
	
}

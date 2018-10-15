package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import tables.TableAllBooks;
import tables.TableAllDVD;
import tables.TableAllFree;
import tables.TableAllLoaned;
import tables.TableAllMedias;

public class SwitchTables {

	//ComboBox um alle Tabellen anzeigen zu lassen / auswählen
	
	public static ComboBox<String> optionsCBox = new ComboBox<>();
	
	static ObservableList<String> options = FXCollections.observableArrayList(
			"All medias", "Loaned", "Free", "All DVD's", "All Books"
		);
	
	public static void comboBox(String name) {

		optionsCBox.setItems(options);
		 
		optionsCBox.setOnAction(e -> {
			switch (optionsCBox.getValue()) {
			case "All medias":
				TableAllMedias.table(name);
				break;
			case "Loaned":
				TableAllLoaned.table(name);
				break;
			case "Free":
				TableAllFree.table(name);
				break;
			case "All DVD's":
				TableAllDVD.table(name);
				break;
			case "All Books":
				TableAllBooks.table(name);
				break;
			}
		});
		
		
	}
	
}

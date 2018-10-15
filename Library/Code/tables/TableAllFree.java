package tables;

import application.AddNew;
import application.Database;
import application.Main;
import application.SwitchTables;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TableAllFree {

	static Scene scene;
	public static TableView table = new TableView();
	public static TableColumn sort = new TableColumn("Sort");
	public static TableColumn title = new TableColumn("Title");
	public static TableColumn author = new TableColumn("Author");
	
	static int i = 0;
	
	//name = eingelogter user
	public static void table(String name) {
		
		String tablename = "All free medias";
		
		Label label = new Label(tablename);
		label.setFont(new Font("Arial", 20));
		
		Database.selectAllFree();
		
		if(i == 0) {
			table.getColumns().addAll(sort, title, author);
			i++;
		} else {
		}
		
		sort.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
		title.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
		author.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
		
		sort.setResizable(false);
		title.setResizable(false);
		author.setResizable(false);
		
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().add(table);
		
		HBox hbox = new HBox();
		hbox.setSpacing(246);
		hbox.setPadding(new Insets(10, 10, 0, 10));
		SwitchTables.comboBox(name);
		hbox.getChildren().addAll(label, SwitchTables.optionsCBox);
		
        HBox hboxAdd = new HBox();
        hboxAdd.setSpacing(120);
        hboxAdd.setPadding(new Insets(0, 10, 10, 10));
        AddNew.addOptions(tablename, name);
        hboxAdd.getChildren().addAll(AddNew.addLoan, AddNew.addMedia, AddNew.addReturn);
		
		BorderPane bpane = new BorderPane();
		bpane.setTop(hbox);
		bpane.setCenter(vbox);
		bpane.setBottom(hboxAdd);
		
		scene = new Scene(bpane, 500, 300);
		
		Main.window.setScene(scene);
		
		
	}
	
}

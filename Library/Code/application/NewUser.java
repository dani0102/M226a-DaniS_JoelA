package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NewUser {

	static Scene scene;
	
	//Neuen User erstellen
	
	static void creatNewUser() {
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
    	Label lname = new Label("Username:");
    	GridPane.setConstraints(lname, 1, 2);
    	
    	TextField nameInput = new TextField();
    	GridPane.setConstraints(nameInput, 2, 2);
    	
    	Label lpassword = new Label("Password:");
    	GridPane.setConstraints(lpassword, 1, 3);
    	
    	PasswordField passwordInput = new PasswordField();
    	GridPane.setConstraints(passwordInput, 2, 3);
    	
    	//User erstellen
    	Button bcreateUser = new Button("Create user");
    	bcreateUser.setOnAction(e -> {
    		String name = nameInput.getText();
    		String password = passwordInput.getText();
    		
    		Database.inserNewUser(name, password); 		
    	});
    	
    	//Abbruch, zurück zu Login
    	Button bcancel = new Button("Cancel");
    	bcancel.setOnAction(e -> {
    		Login.login();
    	});

    	HBox hbox = new HBox();
    	hbox.setSpacing(30);
    	hbox.getChildren().addAll(bcancel, bcreateUser);
    	GridPane.setConstraints(hbox, 2, 4);
    	
    	grid.getChildren().addAll(lname, nameInput, lpassword, passwordInput, hbox);
    	
		scene = new Scene(grid, 280, 150);
		Main.window.setScene(scene);
		
	}
	
}

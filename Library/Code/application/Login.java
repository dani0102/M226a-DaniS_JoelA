package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tables.TableAllMedias;

//Login

public class Login {
	
	static Scene scene;
	
	static void login() {
		
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
    	  	
    	Button blogin = new Button("Log in");
    	blogin.setOnAction(e -> {
    		String name = nameInput.getText();
    		String passwd = passwordInput.getText();
    		
    		//Kontrollieren ob Daten stimmen
    		if(Database.checkLogin(name, passwd) == true) {
    			//Tabelle anzeigen, Zugriff gewährt
    			TableAllMedias.table(nameInput.getText());
    		} else {
    			AlertBox.alertBox("Wrong username or password.");
    		}
    	});
    	
    	//Funktion neuen User erstellen aufrufen
    	Button bnewUser = new Button("New user");
    	bnewUser.setOnAction(e -> {
    		NewUser.creatNewUser();
    	});
    	
    	HBox hbox = new HBox();
    	hbox.setSpacing(33);
    	hbox.getChildren().addAll(bnewUser, blogin);
    	GridPane.setConstraints(hbox, 2, 4);
    	
    	grid.getChildren().addAll(lname, nameInput, lpassword, passwordInput, hbox);
    	
    	scene = new Scene(grid, 280, 150);
    	Main.window.setScene(scene);
    	
	}
	
}

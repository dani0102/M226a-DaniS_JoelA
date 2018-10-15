package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//funktion für einfache alert box

public class AlertBox {

	//text = angezeigter text
	public static void alertBox(String text) {
		
		Stage alert = new Stage();
		alert.initModality(Modality.APPLICATION_MODAL);		//ignore other window action
		
		Label label = new Label(text);
		VBox layout = new VBox(10);
		layout.getChildren().add(label);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout, 200, 50);
		alert.setResizable(false);
		alert.setScene(scene);
		alert.show();
		
	}
	
}

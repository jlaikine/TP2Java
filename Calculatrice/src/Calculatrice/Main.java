package Calculatrice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent rootFXML = FXMLLoader.load(getClass().getResource("Vue.fxml"));
			Scene sceneFXML = new Scene(rootFXML);
			
			//Title de mon application
			primaryStage.setTitle("Calculatrice");
			
			//Attribut la composition de ma fenêtre à ma fenêtre principale
			primaryStage.setScene(sceneFXML);
			
			//Affiche la fenêtre principale
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

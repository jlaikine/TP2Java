package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class VueDetailController 
{
	private Animal selectedAnimal;
	
	@FXML private Label name;
	@FXML private Label age;
	@FXML private Label refuge;
	@FXML private Label sexe;
	@FXML private Label race;
	@FXML private Label taille;

	
	public void initData(Animal animal) {
		selectedAnimal = animal;
		
		name.setText(selectedAnimal.getNom());
		age.setText(Integer.toString(selectedAnimal.getAge()));
		refuge.setText(selectedAnimal.getRefuge());
		sexe.setText(selectedAnimal.getSexe());
		race.setText(selectedAnimal.getRace());
		taille.setText(selectedAnimal.getTaille());
	}
	
	public void changeToDetailledAnimal() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Vue.fxml"));
		Stage window = new Stage();
		Parent FXMLAnimal = loader.load();
		Scene sceneAnimalFXML = new Scene(FXMLAnimal);
		
		window.setTitle("Parc Animalier");
		window.setScene(sceneAnimalFXML);
		window.show();
		
	}
}

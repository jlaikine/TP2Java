package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.*;

public class VueController implements Initializable {

	//Listes des choix
	ObservableList<String> AnimalList = FXCollections.observableArrayList("Chat","Chien");
    ObservableList<String> AgeList = FXCollections.observableArrayList("5","10","15");
    ObservableList<String> SexeList = FXCollections.observableArrayList("Mâle","Femelle");
    ObservableList<String> TailleList = FXCollections.observableArrayList("Petit","Moyen","Grand");
    ObservableList<String> VilleList = FXCollections.observableArrayList("Lyon","Paris","Toulouse");
     
    @FXML
    private Button idFind;
	
	@FXML
    private ChoiceBox<String> idSaisieAnimal;
	
    @FXML
    private ChoiceBox<String> idSaisieAge;
    
    @FXML
    private ChoiceBox<String> idSaisieSexe;
    
    @FXML
    private ChoiceBox<String> idSaisieTaille;
    
    @FXML
    private ChoiceBox<String> idSaisieVille;
    
    @FXML
    private TableView<Animal> tbData;
    
    @FXML
    public TableColumn<Animal, String> animalColId;
    @FXML
    public TableColumn<Animal, String> nomColId;
    @FXML
    public TableColumn<Animal, String> raceColId;
    @FXML
    public TableColumn<Animal, String> ageColId;
    @FXML
    public TableColumn<Animal, String> refugeColId;
    
    @FXML
    public TableColumn<Animal, String> colEdit;
    
    //Liste des animaux objets
    Animal animal1 = new Animal("Chat","Cannelle","photo","Labrador", 2, "Femelle", "Petit","Paris");
	Animal animal2 = new Animal("Chien","Fantasia","photo","Européen", 7, "Mâle", "Moyen","Toulouse");
	Animal animal3 = new Animal("Chien","Bali","photo","Croisé Berger", 8, "Femelle", "Grand","Lyon");
	Animal animal4 = new Animal("Chat","Tygrou","photo","Grison", 14, "Mâle", "Petit","Toulouse");
    
	ObservableList<Animal> listAnimal = FXCollections.observableArrayList(animal1, animal2, animal3, animal4);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		animalColId.setCellValueFactory(new PropertyValueFactory<>("famille"));
		nomColId.setCellValueFactory(new PropertyValueFactory<>("nom"));
	    raceColId.setCellValueFactory(new PropertyValueFactory<>("race"));
	    ageColId.setCellValueFactory(new PropertyValueFactory<>("age"));
	    refugeColId.setCellValueFactory(new PropertyValueFactory<>("refuge"));
	    
	    tbData.setItems(listAnimal);
	    
		idSaisieAnimal.setItems(AnimalList);
        idSaisieAge.setItems(AgeList);
        idSaisieSexe.setItems(SexeList);
        idSaisieTaille.setItems(TailleList);
        idSaisieVille.setItems(VilleList);
        
        
        Callback<TableColumn<Animal, String>, TableCell<Animal, String>> cellFactory=(param) -> {
        	final TableCell<Animal, String> cell = new TableCell<Animal, String>() {
        		@Override
        		public void updateItem(String item, boolean empty) {
        			super.updateItem(item, empty);
        			if(empty) {
        				setGraphic(null);
        				setText(null);
        			}
        			else {
        				final Button editButton = new Button("Voir");
        			
        				editButton.setOnAction(event -> {
        					Animal animal = tbData.getItems().get(getIndex());        					
        					try {
								changeToDetailledAnimal(animal, event);
							} catch (IOException e) {
								e.printStackTrace();
							}
							
            			});
            			
            			setGraphic(editButton);
            			setText(null);
        			}
        		};
        	};
        	return cell;
        };
        
        colEdit.setCellFactory(cellFactory);
        
	};
	
	public void changeToDetailledAnimal(Animal animal, ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("VueDetail.fxml"));
		
		Parent FXMLDetailAnimal = loader.load();
		Scene sceneDetailAnimalFXML = new Scene(FXMLDetailAnimal);
		
		VueDetailController controller = loader.getController();
		controller.initData(animal);
		
		Stage window = new Stage();
		
		window.setTitle("Détail animal");
		window.setScene(sceneDetailAnimalFXML);
		window.show();
	}
	
	public void find() {
		String valueAnimal = idSaisieAnimal.getValue();
		String valueAge = idSaisieAge.getValue();
		String valueSexe = idSaisieSexe.getValue();
		String valueTaille = idSaisieTaille.getValue();
		String valueVille = idSaisieVille.getValue();
		
		ObservableList<Animal> newListAnimal = FXCollections.observableArrayList();
		
		listAnimal.forEach((animal) -> {
			Boolean isFind = true;
			if(valueAnimal != null && animal.famille != valueAnimal) {
				isFind = false;
			}
			if(valueAge != null && animal.age > Integer.parseInt(valueAge)) {
				isFind = false;
			}
			if(valueSexe != null && animal.sexe != valueSexe) {
				isFind = false;
			}
			if(valueTaille != null && animal.taille != valueTaille) {
				isFind = false;
			}
			if(valueVille != null && animal.refuge != valueVille) {
				isFind = false;
			}
			
			if(isFind) {
				newListAnimal.add(animal);
			}
		});
		
		tbData.setItems(newListAnimal);
	}
    
	
}
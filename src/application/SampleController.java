package application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import java.util.List;
import controller.Coordinator;
import model.Toy;

public class SampleController {

    @FXML
    private Button clearButton;


    @FXML
    private ListView<Toy> listView; 

    @FXML
    private TextField nameImput; 

    @FXML
    private RadioButton nameSort;

    @FXML
    private Button removeButton;

    @FXML
    private ListView<Toy> removeListView; 

    @FXML
    private TextField removeSearchBox;

    @FXML
    private Button removeSearchButton;

    @FXML
    private Label removeYouSure;

    @FXML
    private ListView<Toy> resultsListView;

    @FXML
    private Button searchButton;

    @FXML
    private TextField serialNumInput;

    @FXML
    private RadioButton serialNumSort;

    @FXML
    private ToggleGroup sortButtons;

    @FXML
    private TextField typeInput;

    @FXML
    private RadioButton typeSort;

    @FXML
    void clearButtonPressed(ActionEvent event) {

    }

    @FXML
    void removeButtonPressed(ActionEvent event) {

    }

    @FXML
    void removeSearchButtonPressed(ActionEvent event) {

    }

    @FXML
    void searchButtonPressed(ActionEvent event) {
        String searchName = nameImput.getText(); 
        System.out.println("WHY AIN'T THIS SHIT WORKING");

        new Thread(() -> {
            List<Toy> results = Coordinator.searchToysByName(searchName);
            Platform.runLater(() -> {
                resultsListView.setItems(FXCollections.observableArrayList(results));
            });
        }).start();
    }

    @FXML
    void sortButtonPressed(ActionEvent event) {

    }
}

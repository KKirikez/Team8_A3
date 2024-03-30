package application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import controller.Coordinator;
import model.Toy;

public class SampleController implements Initializable {

    @FXML
    private Button clearButton;
    @FXML
    private ListView<Toy> listView;
    @FXML
    private TextField nameInput;
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
    private TextField typeInput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadToysToListView();
    }
    
    private void loadToysToListView() {
        Coordinator.loadToysFromFile(); 
        listView.setItems(FXCollections.observableArrayList(Coordinator.getToys())); 
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
        serialNumInput.setText("");
        nameInput.setText("");
        typeInput.setText("");
    }

    @FXML
    void searchButtonPressed(ActionEvent event) {
        String searchTerm = null;
        String parameterType = null;

        if (!serialNumInput.getText().trim().isEmpty()) {
            searchTerm = serialNumInput.getText().trim();
            parameterType = "Serial";
        } else if (!nameInput.getText().trim().isEmpty()) {
            searchTerm = nameInput.getText().trim();
            parameterType = "Name";
        } else if (!typeInput.getText().trim().isEmpty()) {
            searchTerm = typeInput.getText().trim();
            parameterType = "Type";
        }

        if (searchTerm != null && parameterType != null) {
            List<Toy> results = Coordinator.compareToys(searchTerm, parameterType);
            resultsListView.setItems(FXCollections.observableArrayList(results));
        }
    }
}

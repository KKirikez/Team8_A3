package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SampleController {

    @FXML
    private Button clearButton;

    @FXML
    private ListView<?> listView;

    @FXML
    private TextField nameImput;

    @FXML
    private RadioButton nameSort;

    @FXML
    private Button removeButton;

    @FXML
    private ListView<?> removeListView;

    @FXML
    private TextField removeSearchBox;

    @FXML
    private Button removeSearchButton;

    @FXML
    private Label removeYouSure;

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
    void removeButton(ActionEvent event) {

    }

    @FXML
    void removeSearchButton(ActionEvent event) {

    }

    @FXML
    void searchButtonPressed(ActionEvent event) {

    }

    @FXML
    void sortButtonPressed(ActionEvent event) {

    }

}

package controller;

import builder.Subject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Tomega
 */
public class DependenciesController implements Initializable {

    @FXML
    TextField nameField;
    @FXML
    TextField codeField;
    @FXML
    ListView<String> resultList;
    @FXML
    TextField subDataField;
    @FXML
    TextField depDataField;
    @FXML
    Button clearButton;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resultList = new ListView<>();
        ObservableList<String> results = FXCollections.observableArrayList("csoki", "sajt");
        
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nameField.clear();
                codeField.clear();
                resultList.getItems().clear();
                subDataField.clear();
                depDataField.clear();
                resultList.setItems(results);
                //resultList.setCellFactory();
            }
        });
    }    
    
}

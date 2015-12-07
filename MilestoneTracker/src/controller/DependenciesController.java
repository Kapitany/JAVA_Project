package controller;

import builder.Subject;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.BACK_SPACE;
import javafx.scene.input.KeyEvent;
import main.Launcher;

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
    ListView<Subject> resultList;
    @FXML
    TextField subDataField;
    @FXML
    TextField depDataField;
    @FXML
    Button clearButton;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Subject> results = FXCollections.observableArrayList();
        
        
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nameField.clear();
                codeField.clear();
                resultList.getItems().clear();
                subDataField.clear();
                depDataField.clear();
            }
        });
        
        nameField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                resultList.getItems().clear();
                results.clear();
                for (ArrayList<Subject> list : Launcher.getGraphHandler().getGraphContainer()) {
                    for (Subject subject : list) {
                        if (event.getCharacter().hashCode() == 8) {//ez a backspace kódja
                            if (subject.getSubjectName().contains(nameField.getText())) {
                                results.add(subject);
                            }
                        }
                        else if (subject.getSubjectName().contains(nameField.getText() + event.getCharacter())) {
                            results.add(subject);
                        }
                    }
                }
                resultList.setItems(results);
            }
        });
        
    }    
    
}

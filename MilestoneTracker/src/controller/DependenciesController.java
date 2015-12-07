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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    TextArea subDataField;
    @FXML
    TextArea depDataField;
    @FXML
    Button clearButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Subject> results = FXCollections.observableArrayList();
        clearButton.setId("regbutton");
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
                            if (subject.getSubjectName().contains(nameField.getText()) && subject.getSubjectCode().contains(codeField.getText())) {
                                results.add(subject);
                            }
                        } else if (subject.getSubjectName().contains(nameField.getText() + event.getCharacter()) && subject.getSubjectCode().contains(codeField.getText())) {
                            results.add(subject);
                        }
                    }
                }
                resultList.setItems(results);
            }
        });

        codeField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                resultList.getItems().clear();
                results.clear();
                for (ArrayList<Subject> list : Launcher.getGraphHandler().getGraphContainer()) {
                    for (Subject subject : list) {
                        if (event.getCharacter().hashCode() == 8) {//ez a backspace kódja
                            if (subject.getSubjectCode().contains(codeField.getText()) && subject.getSubjectName().contains(nameField.getText())) {
                                results.add(subject);
                            }
                        } else if (subject.getSubjectCode().contains(codeField.getText() + event.getCharacter()) && subject.getSubjectName().contains(nameField.getText())) {
                            results.add(subject);
                        }
                    }
                }
                resultList.setItems(results);
            }
        });

        resultList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                subDataField.clear();
                subDataField.appendText("Subjectname: " + resultList.getSelectionModel().getSelectedItem().getSubjectName() + "\n");
                subDataField.appendText("Subjectcode: " + resultList.getSelectionModel().getSelectedItem().getSubjectCode() + "\n");
                subDataField.appendText("Subjecttype: " + resultList.getSelectionModel().getSelectedItem().getSubjectType() + "\n");
                subDataField.appendText("Creditvalue: " + resultList.getSelectionModel().getSelectedItem().getCreditValue() + "\n");
                
                //depDataField.appendText("Credits required: " + resultList.getSelectionModel().getSelectedItem().getCreditRequirement() + "\n");
                
                for (ArrayList<Subject> list : Launcher.getGraphHandler().getGraphContainer()) {
                    for (Subject subject : list) {
                        String [] tmpArray = resultList.getSelectionModel().getSelectedItem().getRequirements();
                        for (int i = 0; i < tmpArray.length; i++) {
                            if (tmpArray[i].equals(subject.getSubjectCode())) {
                                depDataField.appendText("Subjectname: " + subject.getSubjectName() + "\n");
                                depDataField.appendText("Subjectcode: " + subject.getSubjectCode() + "\n\n");
                            }
                        }
                    }
                }
                
            }
        });
    }

}

package controller;

import main.Launcher;
import builder.Subject;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Tomega
 */
public class AddNewSubjectController implements Initializable {

    @FXML
    TextField nameField;
    @FXML
    TextField codeField;
    @FXML
    TextField creditValueField;
    @FXML
    TextField requirementsField;
    @FXML
    TextField creditReqField;
    @FXML
    Button registerButton;
    @FXML
    Label subjectType;
    @FXML
    TextArea subjectLst;
    @FXML
    TextArea informationField;
    @FXML
    CheckBox checkBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        subjectType.setText(Launcher.getHandler().getCreditOverflowTo());
        subjectLst.setStyle("-fx-border-color:#000000");
        for (Subject subject : Launcher.getLoader().getSubjectList()) {
            subjectLst.appendText(subject.getSubjectName() + "\n");
        }
        informationField.setPromptText("First rule: Do not try to register a subject with an already stored subject code. Second rule: Fill all the fields! Third rule: The creditvalue and required credits must be numbers!");

        registerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                informationField.clear();
                String checkResults = "";
                boolean checkSuccesfull = true;
                
                for (ArrayList<Subject> list : Launcher.getGraphHandler().getGraphContainer()) {
                    for (Subject subject : list) {
                        if (subject.getSubjectCode().equals(codeField.getText())) {
                            checkResults += "This subject code is already stored!\n";
                            checkSuccesfull = false;
                        }
                    }
                }
                
                if (nameField.getText().isEmpty()
                        || codeField.getText().isEmpty()
                        || creditValueField.getText().isEmpty()
                        || requirementsField.getText().isEmpty()
                        || creditReqField.getText().isEmpty()) {
                    checkResults += "Please fill all the fields!\n";
                    checkSuccesfull = false;
                }
                
                int creditValue = 0 , creditReq = 0;
                if (!(creditValueField.getText().isEmpty() || creditReqField.getText().isEmpty())) {
                    try {
                        creditValue = Integer.valueOf(creditValueField.getText());
                        creditReq = Integer.valueOf(creditReqField.getText());
                    } catch (NumberFormatException ex) {
                        checkResults += "The credit value and required credits are integer values, please use numbers!\n";
                        checkSuccesfull = false;
                    }
                }
                
                String [] requirements = requirementsField.getText().split(";");
                
                if (checkSuccesfull) {
                    Subject subject = new Subject(nameField.getText(), codeField.getText(), creditValue, requirements, subjectType.getText(), creditReq);
                    Launcher.getGraphHandler().getGraphContainer()
                            .get(Launcher.getGraphHandler().getNumSemesters() + Launcher.getGraphHandler().getNumExtraCreditTypes())
                            .add(subject);
                    subjectLst.appendText(nameField.getText() + "\n");
                    if(checkBox.isSelected()) {
                        Launcher.getHandler().getCompletedSubjects().add(subject);
                    }
                    checkResults += "Subject succesfully saved!";
                    informationField.setStyle("-fx-border-color: green");
                }
                else {
                    informationField.setStyle("-fx-border-color: red");
                }
                
                informationField.appendText(checkResults);
            }
            
        });
    }

}

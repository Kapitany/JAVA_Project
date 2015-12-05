/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import handler.Launcher;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
    VBox customSubjectLst;
                    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        subjectType.setText(Launcher.getHandler().getCreditOverflowTo());
        customSubjectLst.setStyle("-fx-border-color:#000000");
        
        
        
        
    }    

}

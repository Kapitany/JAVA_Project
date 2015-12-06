/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import main.Launcher;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Tomega
 */
public class CurriculumMetadataController implements Initializable {

    @FXML
    TextArea metadataContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (String metadata : Launcher.getHandler().getMetadata()) {
            metadataContainer.appendText(metadata + "\n");
        }
        metadataContainer.appendText("This curriculum contains " + Launcher.getLoader().getNumSubjects() + " subjects\n");
        metadataContainer.appendText("The degree can be obtained in " + Launcher.getGraphHandler().getNumSemesters() + " semesters\n");
    }
    
}

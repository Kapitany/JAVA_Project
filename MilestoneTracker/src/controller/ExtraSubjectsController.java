/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import handler.Launcher;
import handler.Subject;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Tomega
 */
public class ExtraSubjectsController implements Initializable {

    @FXML
    GridPane gp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        int numExtraCreditTypes = Launcher.getGraphHandler().getNumExtraCreditTypes();
        String[] extraCreditTypes = Launcher.getGraphHandler().getExtraCreditTypes();
        int numSemesters = Launcher.getGraphHandler().getNumSemesters();
        ArrayList<ArrayList<Subject>> graphContainer = Launcher.getGraphHandler().getGraphContainer();

        for (int i = numSemesters; i < numSemesters + numExtraCreditTypes; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100 / numSemesters);
            RowConstraints rc = new RowConstraints();
            gp.getColumnConstraints().add(column);
        }
        RowConstraints rc = new RowConstraints(30);
        gp.getRowConstraints().add(0, rc);
        int index = 0;
        for (int i = numSemesters; i < numSemesters + numExtraCreditTypes; i++) {
            gp.add(new Label(extraCreditTypes[index]), index, 0);
            index++;
        }

        for (int i = numSemesters; i < numSemesters + numExtraCreditTypes; i++) {
            VBox tmpBox = new VBox();
            for (int j = 0; j < graphContainer.get(i).size(); j++) {
                Text lblSubjetName = new Text(graphContainer.get(i).get(j).getSubjectName());
                tmpBox.getChildren().add(lblSubjetName);
                tmpBox.setAlignment(Pos.CENTER);
                tmpBox.setStyle("-fx-border-color:#000000");
            }
            gp.add(tmpBox, i, 1);
        }
        gp.setAlignment(Pos.CENTER_LEFT);
    }
}

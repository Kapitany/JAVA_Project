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
import javafx.scene.layout.VBox;

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

        gp.setStyle("-fx-border-color:#4444ff");
        for (int i = numSemesters; i < numSemesters + numExtraCreditTypes; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100 / numSemesters);
            gp.getColumnConstraints().add(column);
        }
        /*RowConstraints rc = new RowConstraints(30);
        gp.getRowConstraints().add(0, rc);*/
        int index = 0;
        for (int i = numSemesters; i < numSemesters + numExtraCreditTypes; i++) {
            VBox tmpBox = new VBox();
            Label extraCreditTypeName = new Label(extraCreditTypes[index]);
            extraCreditTypeName.setStyle("-fx-border-color:#44ff44");
            tmpBox.getChildren().add(extraCreditTypeName);

            for (int j = 0; j < graphContainer.get(i).size(); j++) {
                Label lblSubjetName = new Label(graphContainer.get(i).get(j).getSubjectName());
                tmpBox.getChildren().add(lblSubjetName);
                lblSubjetName.setEllipsisString(graphContainer.get(i).get(j).getSubjectName());
                tmpBox.setAlignment(Pos.TOP_CENTER);
                tmpBox.setStyle("-fx-border-color:#000000");
            }
            gp.add(tmpBox, i-numSemesters, 0);
            index++;
        }
        gp.setAlignment(Pos.TOP_LEFT);
    }
}

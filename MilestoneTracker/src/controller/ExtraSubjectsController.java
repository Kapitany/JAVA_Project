package controller;

import main.Launcher;
import builder.Subject;
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

        int index = 0;
        for (int i = numSemesters; i < numSemesters + numExtraCreditTypes; i++) {
            VBox tmpBox = new VBox();
            Label extraCreditTypeName = new Label(extraCreditTypes[index]);
            extraCreditTypeName.setStyle("-fx-font-weight: bold");
            tmpBox.getChildren().add(extraCreditTypeName);

            for (int j = 0; j < graphContainer.get(i).size(); j++) {
                String tempName = graphContainer.get(i).get(j).getSubjectName();
                tempName += " - " + graphContainer.get(i).get(j).getCreditValue();
                Label lblSubjetName = new Label(tempName);
                tmpBox.getChildren().add(lblSubjetName);
                lblSubjetName.setEllipsisString(tempName);
                tmpBox.setAlignment(Pos.TOP_CENTER);
            }
            gp.add(tmpBox, i-numSemesters, 0);
            index++;
        }
        gp.setAlignment(Pos.CENTER);
    }
}

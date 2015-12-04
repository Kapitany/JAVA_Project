package controller;

import handler.Launcher;
import handler.Subject;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author adamp
 */
public class GraphController implements Initializable {

    @FXML
    GridPane pane;

    @FXML
    AnchorPane ap;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        int numSemesters = Launcher.getGraphHandler().getNumSemesters();
        for (int i = 0; i < numSemesters; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100/numSemesters);
            RowConstraints rc = new RowConstraints();
            pane.getColumnConstraints().add(column);
        }
        RowConstraints rc = new RowConstraints(30);
        pane.getRowConstraints().add(0, rc);
        for (int i = 0; i < numSemesters; i++) {
            pane.add(new Label((i + 1) + ". félév"), i, 0);
        }
        
        ArrayList<ArrayList<Subject>> subjectsPerSemester = Launcher.getGraphHandler().getGraphContainer();

        for (int semester = 0; semester < numSemesters; semester++) {
            VBox tmpBox = new VBox();
            for (int subj = 0; subj < subjectsPerSemester.get(semester).size(); subj++) {

                Text lblSubjetName = new Text(subjectsPerSemester.get(semester).get(subj).getSubjectName());
                tmpBox.getChildren().add(lblSubjetName);
                tmpBox.setAlignment(Pos.CENTER);
                tmpBox.setStyle("-fx-border-color:#000000");
                
            }
            pane.add(tmpBox, semester, 1);
        }

        pane.setAlignment(Pos.CENTER_LEFT);
    }
}

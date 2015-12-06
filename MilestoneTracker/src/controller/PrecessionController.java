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
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author adamp
 */
public class PrecessionController implements Initializable {

    @FXML
    GridPane pane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        pane.setStyle("-fx-border-color:#ff4444");

        int numSemesters = Launcher.getGraphHandler().getNumSemesters();
        for (int i = 0; i < numSemesters/2+1; i++) {
            ColumnConstraints column = new ColumnConstraints();
            pane.getColumnConstraints().add(column);
        }

        
        ArrayList<ArrayList<Subject>> subjectsPerSemester = Launcher.getGraphHandler().getGraphContainer();

        for (int semester = 0; semester < numSemesters; semester++) {
            VBox tmpBox = new VBox();
            Label semesterCount = new Label((semester + 1) + ". félév");
            semesterCount.setStyle("-fx-font-weight: bold");
            tmpBox.getChildren().add(semesterCount);
            
            for (int subj = 0; subj < subjectsPerSemester.get(semester).size(); subj++) {
                Label lblSubjetName;

                String tempName = subjectsPerSemester.get(semester).get(subj).getSubjectName();
                tempName += " - " +subjectsPerSemester.get(semester).get(subj).getCreditValue();
                if(tempName.length() >= 25){ 
                    lblSubjetName = new Label();                  
                    lblSubjetName.setText(tempName);
                    lblSubjetName.setEllipsisString(tempName);
                    lblSubjetName.setFont(new Font(10));
                }else{
                    lblSubjetName = new Label(tempName);
                    lblSubjetName.setFont(new Font(10));
                }
                tmpBox.getChildren().add(lblSubjetName);
                
                tmpBox.setAlignment(Pos.CENTER);
                
            }
            
            if(semester <= (numSemesters/2)){
                pane.add(tmpBox, semester, 0);
            }else{
                pane.add(tmpBox, semester-(numSemesters/2)-1, 1);
            }
        }
        
        pane.setAlignment(Pos.CENTER);
    }
}

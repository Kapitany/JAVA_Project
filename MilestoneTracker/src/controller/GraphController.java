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
import javafx.scene.layout.Background;
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

        pane.setStyle("-fx-border-color:#ff4444");

        int numSemesters = Launcher.getGraphHandler().getNumSemesters();
        for (int i = 0; i < numSemesters/2+1; i++) {
            ColumnConstraints column = new ColumnConstraints();
            //column.setPercentWidth(100/(numSemesters/2+1));
            pane.getColumnConstraints().add(column);
        }
        /*RowConstraints rc = new RowConstraints(100);
        pane.getRowConstraints().add(0, rc);*/
        
        
        ArrayList<ArrayList<Subject>> subjectsPerSemester = Launcher.getGraphHandler().getGraphContainer();

        for (int semester = 0; semester < numSemesters; semester++) {
            VBox tmpBox = new VBox();
            Label semesterCount = new Label((semester + 1) + ". félév");
            semesterCount.setStyle("-fx-border-color:#44ff44");
            tmpBox.getChildren().add(semesterCount);
            
            for (int subj = 0; subj < subjectsPerSemester.get(semester).size(); subj++) {
                Label lblSubjetName;

                String tempName = subjectsPerSemester.get(semester).get(subj).getSubjectName();
                tempName += " - " +subjectsPerSemester.get(semester).get(subj).getCreditValue();
                //lblSubjetName = new Text(tempName);
                //lblSubjetName.setStyle("-fx-border-color:#000000");
                if(tempName.length() >= 25){
                    //String [] tempNameArray = tempName.split(" ");
                    lblSubjetName = new Label();
                    /*tempName = tempNameArray[0];
                    for(int i = 1; i < tempNameArray.length; i++){
                        tempName += "\n"+tempNameArray[i];
                    }*/
                    //lblSubjetName.setStyle("-fx-border-color:#000000");
                    lblSubjetName.setText(tempName);
                    lblSubjetName.setEllipsisString(tempName);
                }else{
                    lblSubjetName = new Label(tempName);
                }
//                Label lblSubjetName = new Label("Alma");
                tmpBox.getChildren().add(lblSubjetName);
                
                tmpBox.setAlignment(Pos.CENTER);
                //tmpBox.setStyle("-fx-border-color:#44ff44");
                
            }

            System.out.println(tmpBox.isResizable());
            //tmpBox.resizeRelocate(0, 0, 600, 600);
            System.out.println(tmpBox.getHeight());
            if(semester <= (numSemesters/2)){
                pane.add(tmpBox, semester, 0);
            }else{
                pane.add(tmpBox, semester-(numSemesters/2)-1, 1);
            }
        }
        
        pane.setAlignment(Pos.CENTER_LEFT);
    }
}

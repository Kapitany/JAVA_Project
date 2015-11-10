package controller;

import handler.GraphFileHandler;
import handler.LogicalCurriculumHandler;
import handler.Subject;
import handler.SubjectListLoader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author adamp
 */
public class GraphController implements Initializable {

    @FXML
    private VBox vBoxSemester1;
    @FXML
    private VBox vBoxSemester2;
    @FXML
    private VBox vBoxSemester3;
    @FXML
    private VBox vBoxSemester4;
    @FXML
    private VBox vBoxSemester5;
    @FXML
    private VBox vBoxSemester6;
    @FXML
    private VBox vBoxSemester7;
    @FXML
    private GridPane gridp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<VBox> vBoxSemesters = new ArrayList<>();

        vBoxSemesters.add(vBoxSemester1);
        vBoxSemesters.add(vBoxSemester2);
        vBoxSemesters.add(vBoxSemester3);
        vBoxSemesters.add(vBoxSemester4);
        vBoxSemesters.add(vBoxSemester5);
        vBoxSemesters.add(vBoxSemester6);
        vBoxSemesters.add(vBoxSemester7);

        try {

            LogicalCurriculumHandler handler = new LogicalCurriculumHandler();
            SubjectListLoader loader = new SubjectListLoader(handler.getSubjPath());
            GraphFileHandler graphHandler = new GraphFileHandler(loader.getSubjectList());

            ArrayList<ArrayList<Subject>> subjectsPerSemester = graphHandler.getGraphContainer();
            
            for (int semester = 0; semester < 7; semester++) {
                for (int subj = 0; subj < subjectsPerSemester.get(semester).size(); subj++) {
                   
                    Label lblSubjetName = new Label(subjectsPerSemester.get(semester).get(subj).getSubjectName());
                    lblSubjetName.setWrapText(true);
                    lblSubjetName.setTextAlignment(TextAlignment.CENTER);
                    lblSubjetName.setAlignment(Pos.CENTER);
                    vBoxSemesters.get(semester).getChildren().add(lblSubjetName);
                    vBoxSemesters.get(semester).setAlignment(Pos.TOP_CENTER);
                    
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(GraphController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}

package handler;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;

/**
 * FXML Controller class
 *
 * @author Tomega
 */
public class GraphViewController implements Initializable {

    @FXML
    FlowPane pane;
    
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void initialize(URL url, ResourceBundle rb) {
        try {
            System.out.println("in graphcontroller:");
            LogicalCurriculumHandler handler = new LogicalCurriculumHandler();
            SubjectListLoader loader = new SubjectListLoader(handler.getSubjPath());
            GraphFileHandler graphHandler = new GraphFileHandler(loader.getSubjectList());
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            pane.setPrefWidth(primaryScreenBounds.getWidth());
            for (int i = 0; i < graphHandler.getGraphContainer().size(); i++) {
                Label label = new Label("List"+(i+1));
                label.setPrefWidth(pane.getPrefWidth() / graphHandler.getGraphContainer().size());
                pane.getChildren().add(label);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
    
}

package handler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author tomega
 */
//A kikommentezett sorok egyenként is sok sor kimenettel rendelkeznek. Szemléltető jellegűek, a tesztelést szolgálják/ták.
public class MainClass extends Application {

    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void start(Stage primaryStage) throws Exception {
        try {
            LogicalCurriculumHandler handler = new LogicalCurriculumHandler();
            handler.list();
            SubjectListLoader loader = new SubjectListLoader(handler.getSubjPath());
//            loader.listSubjects();
//            DependenceViewer viewer = new DependenceViewer(loader.getSubjectList());
            GraphFileHandler graphHandler = new GraphFileHandler(loader.getSubjectList());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Initialisation aborted!");
        }
        Parent root = FXMLLoader.load(getClass().getResource("graphView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

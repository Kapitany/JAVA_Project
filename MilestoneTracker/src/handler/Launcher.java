package handler;

import java.util.ArrayList;
import java.util.Arrays;
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
public class Launcher extends Application {

    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        launch(args);
    }
    
    private static GraphFileHandler graphHandler = null;
    private static LogicalCurriculumHandler handler = null;
    private static SubjectListLoader loader = null;

    public static LogicalCurriculumHandler getHandler() {
        return handler;
    }

    public static SubjectListLoader getLoader() {
        return loader;
    }

    public static GraphFileHandler getGraphHandler() {
        return graphHandler;
    }

    private static final String source = "mernokinfoBSC#V1_1";

    public static String getSource() {
        return source;
    }
    
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void start(Stage primaryStage) throws Exception {
        try {
            //itt a 3 inicializálás elleőrzés jellegű, ha valamelyik fájl korrput akkor még itt kilép és nem lesz később baj
            System.out.println("in launcher:");
            if (handler == null) {
                handler = new LogicalCurriculumHandler(source); //TODO itt majd lehet a tallózást beállítani más projektekre
            }
//            for (int i = 0; i < handler.getMetadata().length; i++) {
//                System.out.println(handler.getMetadata()[i]);
//            }
            if (loader == null) {
                loader = new SubjectListLoader(handler.getSubjPath());
            }
//            loader.listSubjects();
//            DependenceViewer viewer = new DependenceViewer(loader.getSubjectList());
            if (graphHandler == null) {
                graphHandler = new GraphFileHandler(loader.getSubjectList(), handler.getCurrPath());
            }
            graphHandler.getGraphContainer().add(new ArrayList<>());    //A custom tárgyak felvételéhez + 1 lista hozzáadva
            System.out.println("Initialization in launcher completed without errors!");

            /*EDDIG A PONTIG TART AZ INICIALIZÁLÁS*/
            Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle(handler.getCurriculumName());
//            primaryStage.setMaximized(true);
//            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Initialisation aborted!");
        }
    }

}
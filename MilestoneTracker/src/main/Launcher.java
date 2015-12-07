package main;

import builder.LogicalCurriculumHandler;
import builder.SubjectListLoader;
import builder.GraphFileHandler;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
            System.out.println("Initializing....");
            if (handler == null) {
                handler = new LogicalCurriculumHandler(source); //TODO itt majd lehet a tallózást beállítani más projektekre
            }
//            handler.list();
            if (loader == null) {
                loader = new SubjectListLoader(handler.getSubjPath());
            }
//            loader.listSubjects();
//            DependenceViewer viewer = new DependenceViewer(loader.getSubjectList());
            if (graphHandler == null) {
                graphHandler = new GraphFileHandler(loader.getSubjectList(), handler.getCurrPath());
            }
            graphHandler.getGraphContainer().add(new ArrayList<>());    //A custom tárgyak felvételéhez + 1 lista hozzáadva
            
            /*EDDIG A PONTIG TART AZ INICIALIZÁLÁS*/
            System.out.println("Initialization in launcher completed without errors!");
            System.out.println("----------------------------------------------------");
            System.out.println("STATISTICS:");
            System.out.println("----------------------------------------------------");
            System.out.println("All received credits: " + handler.getCreditsReceived());
            System.out.println("to 100%: [at least]" + handler.getCreditsToReceive());
            System.out.println("----------------------------------------------------");
            //System.out.println("Specific credits: " + handler.getPerTypeCounter());
            System.out.println("to 100%: " + handler.getCreditTypes());
            System.out.println("----------------------------------------------------");
            System.out.println("Overflow goes to: " + handler.getCreditOverflowTo());
            System.out.println("Extra 0 credit subjects to complete [by code]: " + handler.getListExtraReqs());
            System.out.println("----------------------------------------------------");
            System.out.println("Completed global requirements: " + handler.getNumCompletedGlobalReqs());
            System.out.println("Number of global requirements to 100%: " + handler.getNumGlobalReqs());
            System.out.println("----------------------------------------------------");
            
            
            Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("cap.png"));
            scene.getStylesheets().add("TrackerStyle.css");
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

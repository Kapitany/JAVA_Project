package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import main.Launcher;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Tomega
 */
public class CurriculumMetadataController implements Initializable {

    @FXML
    TextArea metadataContainer;

    @FXML
    TextArea documentationContainer;

    @FXML
    TextArea builderFilesContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (String metadata : Launcher.getHandler().getMetadata()) {
            metadataContainer.appendText(metadata + "\n");
        }
        metadataContainer.appendText("This curriculum contains " + Launcher.getLoader().getNumSubjects() + " subjects\n");
        metadataContainer.appendText("The degree can be obtained in " + Launcher.getGraphHandler().getNumSemesters() + " semesters\n");

        try {
            String line;
            documentationContainer.appendText("******************************************\n");
            documentationContainer.appendText("CURRICULUM DOCUMENTATION\n");
            documentationContainer.appendText("******************************************\n");
            BufferedReader reader = new BufferedReader(new FileReader(Launcher.getSource() + "//documentation.txt"));
            while ((line = reader.readLine()) != null) {
                documentationContainer.appendText(line + "\n");
            }
            reader = new BufferedReader(new FileReader(Launcher.getSource() + "//logikai.txt"));
            while ((line = reader.readLine()) != null) {
                builderFilesContainer.appendText(line + "\n");
            }
            builderFilesContainer.appendText("\n\n");
            reader = new BufferedReader(new FileReader(Launcher.getSource() + "//graf.txt"));
            while ((line = reader.readLine()) != null) {
                builderFilesContainer.appendText(line + "\n");
            }
            builderFilesContainer.appendText("\n\n");
            reader = new BufferedReader(new FileReader(Launcher.getSource() + "//websites.txt"));
            while ((line = reader.readLine()) != null) {
                builderFilesContainer.appendText(line + "\n");
            }
        } catch (FileNotFoundException ex) {
            //ez soha nem fog megtörténni, inicializáláskor le van ellenőrizve
        } catch (IOException ex) {
            System.out.println("Unexpected IOException in CurriculumMetadateController, at documentation-reader!");
        }
    }

}

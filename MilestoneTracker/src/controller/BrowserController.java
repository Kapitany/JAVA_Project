package controller;

import main.Launcher;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Tomega
 */
public class BrowserController implements Initializable {

    @FXML
    WebView browser;

    @FXML
    ChoiceBox<MenuItem> siteChooser;

    WebEngine webEngine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        class CustomMenuItem extends MenuItem {

            private CustomMenuItem(String nameAndURL) {
                super(nameAndURL);
            }

            @Override
            public String toString() {
                return this.getText();
            }

        }

        BufferedReader reader = null;
        try {
            webEngine = browser.getEngine();
            File URLfile = new File(Launcher.getSource() + "//websites.txt");
            reader = new BufferedReader(new FileReader(URLfile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] nameAndURL = line.split(";");
                CustomMenuItem site = new CustomMenuItem(nameAndURL[0]);
                site.setUserData(nameAndURL[1]);
                siteChooser.getItems().add(site);
                webEngine.load((String) site.getUserData());
                siteChooser.setValue(site);
            }

            siteChooser.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    webEngine.load((String) siteChooser.getItems().get(newValue.intValue()).getUserData());
                }
            });
        } catch (Exception ex) {
            webEngine.loadContent("<br><br><br>Unexpected exception occured while trying to load the browser."
                    + "<br>The probable causes are:"
                    + "<br>1: Your curriculum, is corrupted, the 'website.txt' file is missing!"
                    + "<br>2: You don't have a valid internet connection"
                    + "<br>NO VIEW AVAILABLE");
        } finally {
            try {
                reader.close();
            } catch (Exception ex) {
                webEngine.loadContent("<br><br><br>Unexpected exception occured while trying to load the browser."
                        + "<br>The probable causes are:"
                        + "<br>1: Your curriculum, is corrupted, the 'website.txt' file is missing!"
                        + "<br>2: You don't have a valid internet connection"
                        + "<br>NO VIEW AVAILABLE");
            }
        }
    }

}

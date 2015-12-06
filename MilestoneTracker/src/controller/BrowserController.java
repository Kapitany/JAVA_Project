/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import main.Launcher;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BufferedReader reader = null;
        try {
            WebEngine webEngine = browser.getEngine();
            File URLfile = new File(Launcher.getSource() + "//website.txt");
            reader = new BufferedReader(new FileReader(URLfile));
            webEngine.load(reader.readLine());
        } catch (FileNotFoundException ex) {
            System.out.println("Your curriculum, is coeeupted, the 'website.txt' file is missing!");
            System.out.println("Please update!");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}

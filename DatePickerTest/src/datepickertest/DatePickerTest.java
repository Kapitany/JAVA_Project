/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datepickertest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Otti
 */
public class DatePickerTest extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        DatePicker calendar = new DatePicker();
        
        StackPane root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        root.getChildren().addAll(calendar);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexzune;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Acer
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label2;
    @FXML
    private Label label;
    @FXML
    private Label image;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        label.setOnMouseEntered((MouseEvent e)->{
            label.setScaleX(1.2);
            label.setScaleY(1.2);
        });
        
        label.setOnMouseExited((MouseEvent e)->{
            label.setScaleX(1);
            label.setScaleY(1);
        });
        //berakom Saitama-t
        Image pic=new Image(getClass().getResourceAsStream("Saitama_OK_small.jpg"));
        image.setGraphic(new ImageView(pic));
        
        image.setOnMouseEntered((MouseEvent e)->{
            image.setOpacity(0.5);
            image.setScaleX(1.5);
            image.setScaleY(1.5);
            image.setRotate(-90);
        });
        image.setOnMouseExited((MouseEvent e)->{
            image.setOpacity(1);
            image.setScaleX(1);
            image.setScaleY(1);
            image.setRotate(0);
        });
        
        label2.setOnMouseEntered((MouseEvent e) -> {
            label2.setScaleX(1.2);
            label2.setScaleY(1.2);
        });
        
        label2.setOnMouseExited((MouseEvent e) -> {
            label2.setScaleX(1);
            label2.setScaleY(1);
        });
    }    
    
}

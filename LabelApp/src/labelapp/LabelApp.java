/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labelapp;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.effect.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Acer
 */
public class LabelApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Label label= new Label(); //adott label létrehozása
        label.setId("label"); //azonosító hozzárendelés a skinezéshez, ott #label hivatkozik css-ben
        label.setText("Label");
        
        Label label1= new Label();
        label1.setId("label1"); //id hozzárendelés #label1 css-ben
        label1.setText("Label1");
        //label1.setTranslateX(100); //itt állítasz pozíciót
        
        Label label2=new Label(); //swag label incoming
        label2.setId("label2");
        label2.setText("Label2");
        
        /*label2.setTranslateY(60);
        label2.setTranslateX(40);*/
        // mikor rámegy az egér megnöveli a méretét, mikor kilép visszaméretezi
        label2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                label2.setScaleX(1.5);
                label2.setScaleY(1.5);
                
        }
        });
        
        label2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                label2.setScaleX(1);
                label2.setScaleY(1);
                
        }
        });
        
        
        
        Label label4=new Label();
        label4.setText("label4-ASD");
        Label label5=new Label();
        label5.setText("label5-[$( ͡° ͜ʖ ͡°)$]");
        Label label6=new Label();
        label6.setText("label6-no waifu");
        Label label7=new Label();
        label7.setText("label7-no laifu");
        label6.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                label6.setScaleX(1.5);
                label6.setScaleY(1.5);
                
        }
        });
        
        label6.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                label6.setScaleX(1);
                label6.setScaleY(1);
                
        }
        });
        label7.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                label7.setScaleX(1.5);
                label7.setScaleY(1.5);
                
        }
        });
        
        label7.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                label7.setScaleX(1);
                label7.setScaleY(1);
                
        }
        });
        Label label8=new Label();
        label8.setText("label8-jó hosszú szöveg");
        Label label9=new Label();
        label9.setText("label9-☐ Not REKT");
        Label label10=new Label();
        label10.setText("label10-☑ REKT");
        Label label11=new Label();
        label11.setText("label11- ☑ Tyrannosaurus REKT");
        
        
        
        HBox hbox=new HBox(10);//horizontális box amibe majd vbox-ot raksz(többet, ez megy ki root helyett)
        
        VBox vbox= new VBox();// layout menendzselés vbox-al
        vbox.setId("vbox");
        //vbox.setPadding(new Insets(0,20,10,200)); //top,right,bottom,left
        vbox.getChildren().addAll(label,label4);
        
        VBox vbox1= new VBox();
        vbox1.setId("vbox1");
        vbox1.getChildren().addAll(label1,label5,label8);
        
        VBox vbox2= new VBox();
        vbox2.setSpacing(5);
        vbox2.setId("vbox2");
        vbox2.getChildren().addAll(label2,label6,label7);
        //ha ugyanazt az effektet raktam volna erre, mint az itteni label-ekre
        //akkor szörnyű lett volna (ezért vanmindegyiken külön), de így szebb
        
        VBox vbox3=new VBox();
        vbox3.setId("vbox3");
        vbox3.getChildren().addAll(label9,label10,label11);
        
        hbox.getChildren().addAll(vbox,vbox1,vbox2,vbox3);
        
        //a dobozok mérete sokszor függ a betűtípustól is, nem csak a mérettől
        
        Scene scene = new Scene(hbox, 750, 500);
        scene.getStylesheets().add("mystyle.css"); //itt adod meg a css file nevét
        //css-t STYLESHEET néven keresd :D
        
        primaryStage.setTitle("Label Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

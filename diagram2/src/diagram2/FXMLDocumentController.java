/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagram2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Kapitany
 */
public class FXMLDocumentController implements Initializable {
    
    private DoubleProperty progressValue = new SimpleDoubleProperty();
    private int cred1 = 0;
    private int cred2 = 0;
    private int cred3 = 0;
    
    private int credmax1 = 80;
    private int credmax2 = 50;
    private int credmax3 = 90;
    

    public final double getprogressValue() {
        return progressValue.get();
    }

    public final void setprogressValue(double value) {
        progressValue.set(value);
    }

    public DoubleProperty progressValueProperty() {
        return progressValue;
    }
    
    
    @FXML
    private PieChart chart;
    
    public int maxcred() {
        return credmax1 + credmax2 + credmax3;
    }
    
    public  void calculator(int type) {
        if (type == 1) {
            if (cred1 < credmax1) {
                cred1 += 4;
            }
        } else if (type == 2) {
            if (cred2 < credmax2) {
                cred2 += 2;
            }
        }else if (type == 3) {
            if (cred3 < credmax3) {
                cred3 += 3;
            }
        }
         setprogressValue(cred1 + cred2 + cred3);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Kész", 0),
                        new PieChart.Data("Hátra van", 100));
        
        chart.setData(pieChartData);
        
        progressValue.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal) {
                if (getprogressValue() >= 0 && getprogressValue() <= maxcred()) {
                    chart.getData().get(0).setPieValue(getprogressValue());
                    chart.getData().get(1).setPieValue(maxcred() - getprogressValue());
                    chart.getData().get(0).setName("Kész : " + getprogressValue()/maxcred() + " %");
                    kesz.setText("Kész:         " + (int)(getprogressValue()/maxcred() * 100) + " %");
                    hatra.setText("Hátra van: " + (100 - ((int)(getprogressValue()/maxcred() * 100))) + " %");
                } else {
                    System.out.println("The progress is out of range!!\n Auto correct!");
                    if (getprogressValue() < 0) {
                        setprogressValue(0);
                        System.out.println("Progress set to 0");
                    } else {
                        setprogressValue(100);
                        System.out.println("Progress set to 100");
                    }
                }
            }
        });
        
        but.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        calculator(1);
                    }
                });
        
        but2.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        calculator(2);
                    }
                });
        
        but3.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        calculator(3);
                    }
                });
        
        
    }    
    
    @FXML
    private Label kesz;
    
    @FXML
    private Label hatra;
    
    @FXML
    private Button but;
    
    @FXML
    private Button but2;
    
    @FXML
    private Button but3;
    
    
    
}

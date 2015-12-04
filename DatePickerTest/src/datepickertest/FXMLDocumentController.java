/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datepickertest;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;

/**
 *
 * @author Otti
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    DatePicker datepicker;
    @FXML
    ChoiceBox<String> choiceBox;
     
    public FXMLDocumentController(){
        
    }
    
    //új esemény hozzáadása (combobox alapján történő esemény (évi, havi, heti, napi))
    public void setIntv(DateCell dc, LocalDate item ){     
        String intv = choiceBox.getValue();
        if(intv.equals("Heti")){
            /*if(MonthDay.from(item).equals(MonthDay.of(Month.MARCH, dayOfMonth))){
                
            }*/
        }else if(intv.equals("Napi")){
            
        }else if(intv.equals("Havi")){
            for (int i = 0; i < 10; i++) {
                if(MonthDay.from(item).equals(MonthDay.of(i, 12))){
                    dc.setTooltip(new Tooltip("Happy Birthday!"));
                    dc.setStyle("-fx-background-color: #ff4444;");
                }
            }
        }else if(intv.equals("Évi")){
            if(MonthDay.from(item).equals(MonthDay.of(12, 25))){
                dc.setTooltip(new Tooltip("Happy Birthday!"));
                    dc.setStyle("-fx-background-color: #ff4444;");
            }
        }
    }
    
    
    
    final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
        public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
                @Override public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    setIntv(this, item);
                    /*if (MonthDay.from(item).equals(MonthDay.of(12, 25))) {
                        setTooltip(new Tooltip("Happy Birthday!"));
                        setStyle("-fx-background-color: #ff4444;");
                    }
                    if (item.equals(LocalDate.now().plusDays(1))) {
                        // Tomorrow is too soon.
                        setDisable(true);
                    }*/
                }
            };
        }
    };
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialize...");
        
        ObservableList<String> dayList = FXCollections.observableArrayList("Napi", "Heti", "Havi", "Évi");
        
        choiceBox.setItems(dayList);
        
        datepicker.setDayCellFactory(dayCellFactory);
        
        
        
        datepicker.setValue(LocalDate.of(2015, 12, 8));
        datepicker.setValue(LocalDate.of(2015, 12, 10));
        //datepicker.setTooltip(new Tooltip("Tooltip"));

        datepicker.setShowWeekNumbers(true);
        //datepicker.show();
        datepicker.requestFocus();
    }    
    
    

}

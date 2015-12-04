/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datepickertest;

import java.net.URL;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    ComboBox<String> combobox;
     
    public FXMLDocumentController(){
        
    }
    
    private void handleButtonAction(ActionEvent event) {
        //naptári bejegyzések tárolóból kiszedni az aktuális napra vonatkozó adatokat
        
    }
    
    final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
        public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
                @Override public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    if (MonthDay.from(item).equals(MonthDay.of(9, 25))) {
                        setTooltip(new Tooltip("Happy Birthday!"));
                        setStyle("-fx-background-color: #ff4444;");
                    }
                    if (item.equals(LocalDate.now().plusDays(1))) {
                        // Tomorrow is too soon.
                        setDisable(true);
                    }
                }
            };
        }
    };
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialize...");
        
        //combobox.getItems().addAll("Day", "Week");
        
        
        datepicker.setDayCellFactory(dayCellFactory);
        
        
        datepicker.setValue(LocalDate.of(2015, 12, 8));
        datepicker.setValue(LocalDate.of(2015, 12, 10));
        datepicker.setTooltip(new Tooltip("Tooltip"));

        datepicker.setShowWeekNumbers(true);
        //datepicker.show();
        datepicker.requestFocus();
    }    
    
}

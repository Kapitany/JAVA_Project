/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import builder.CalendarEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Tomega
 */
public class CalendarController implements Initializable {

    @FXML
    DatePicker datepicker;
    DatePicker datePicker1;
    @FXML
    ChoiceBox<String> choiceBox;

    @FXML
    private Button but;
    @FXML
    private TextField eventNameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private ChoiceBox timeChoiceFrom;
    @FXML
    private ChoiceBox timeChoiceTo;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    public ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>();

    Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
        public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    setIntv(this, item, events);
                }
            };
        }
    };

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadEvents(events);

        ObservableList<String> dayList = FXCollections.observableArrayList("None"/*, "Dayly"*/, "Weekly", "Yearly");

        choiceBox.setItems(dayList);
        choiceBox.setValue("None");

        ObservableList<String> timeList = FXCollections.observableArrayList("00:00", "02:00", "04:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");
        timeChoiceFrom.setItems(timeList);
        timeChoiceFrom.setValue("08:00");
        timeChoiceTo.setItems(timeList);
        timeChoiceTo.setValue("08:00");

        datepicker.setShowWeekNumbers(true);

        //System.out.println("Datecellfactory started");
        datepicker.setDayCellFactory(dayCellFactory);
        but.setId("regbutton");
        but.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        addEvent(events);
                        eventSaver(events);
                    }
                });
    }

    public void loadEvents(ArrayList<CalendarEvent> events) {
        eventReader("calendarEvents.txt", events);
        //System.out.println("Events loaded");
    }

    public void addEvent(ArrayList<CalendarEvent> events) {
        //System.out.println(eventNameTextField.getText() + " " + descriptionTextArea.getText() + " " + choiceBox.getValue() + " " + startDatePicker.getValue() + " " + timeChoiceFrom.getValue());
        String stTime = startDatePicker.getValue() + " " + timeChoiceFrom.getValue();
        String endTime = endDatePicker.getValue() + " " + timeChoiceTo.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(stTime, formatter);
        LocalDateTime eTime = LocalDateTime.parse(endTime, formatter);
        events.add(events.size(), new CalendarEvent(eventNameTextField.getText(), descriptionTextArea.getText(), choiceBox.getValue(), startTime, eTime));
        datepicker.setDayCellFactory(dayCellFactory);
    }

    public void eventSaver(ArrayList<CalendarEvent> events) {

        BufferedWriter writer = null;
        try {
            File file = new File("calendarevents.txt");
            //System.out.println(file.getCanonicalPath());
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException ex) {
            Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                for (int i = 0; i < events.size(); i++) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    String sDate = events.get(i).getStartDate().format(formatter);
                    String eDate = events.get(i).getEndDate().format(formatter);
                    writer.newLine();
                    writer.write(events.get(i).getEventName() + "%%" + events.get(i).getDescription() + "%%" + events.get(i).getType() + "%%" + sDate + "%%" + eDate);
                }
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<CalendarEvent> eventReader(String source, ArrayList<CalendarEvent> events) {
        BufferedReader reader = null;
        try {
            File file = new File(source);
            //System.out.println(file.getCanonicalPath());
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException ex) {
            Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                String temp = reader.readLine();
                int i = 0;
                while (temp != null) {
                    String[] tempSplit = temp.split("%%");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime stld = LocalDateTime.parse(tempSplit[3], formatter);
                    LocalDateTime endld = LocalDateTime.parse(tempSplit[4], formatter);

                    events.add(i, new CalendarEvent(tempSplit[0], tempSplit[1], tempSplit[2], stld, endld));
                    temp = reader.readLine();
                    i++;
                }

                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return events;
    }

    public void setIntv(DateCell dc, LocalDate item, ArrayList<CalendarEvent> events) {
        String intv;
        LocalDateTime startTime;
        LocalDateTime endTime;
        //System.out.println(item);
        for (int i = 0; i < events.size(); i++) {
            intv = events.get(i).getType();
            startTime = events.get(i).getStartDate();
            endTime = events.get(i).getEndDate();
            if (intv.equals("None")) {
                if (MonthDay.from(item).equals(MonthDay.of(startTime.getMonth(), startTime.getDayOfMonth()))) {
                    dc.setTooltip(new Tooltip(events.get(i).getEventName() + "\n" + events.get(i).getDescription()));
                    dc.setStyle("-fx-background-color: #4444ff;");
                }
            } else if (intv.equals("Weekly")) {
                if (MonthDay.from(item).equals(MonthDay.of(startTime.getMonth(), startTime.getDayOfMonth()))) {
                    dc.setTooltip(new Tooltip(events.get(i).getEventName() + "\n" + events.get(i).getDescription()));
                    dc.setStyle("-fx-background-color: #44ff44;");
                } else {
                    do {
                        if (MonthDay.from(item).equals(MonthDay.of(startTime.plusDays(7).getMonth(), startTime.plusDays(7).getDayOfMonth()))) {
                            dc.setTooltip(new Tooltip(events.get(i).getEventName() + "\n" + events.get(i).getDescription()));
                            dc.setStyle("-fx-background-color: #44ff44;");
                        }
                        startTime = startTime.plusDays(7);
                    } while (MonthDay.of(startTime.getMonth(), startTime.getDayOfMonth()).isBefore(MonthDay.of(12, 12)));
                }
            } else if (intv.equals("Yearly")) {
                if (MonthDay.from(item).equals(MonthDay.of(12, 25))) {
                    dc.setTooltip(new Tooltip(events.get(i).getDescription()));
                    dc.setStyle("-fx-background-color: #ff4444;");
                }
            } else if (intv.equals("Dayly")) {
                if (MonthDay.from(item).equals(MonthDay.of(startTime.getMonth(), startTime.getDayOfMonth()))) {
                    dc.setTooltip(new Tooltip(events.get(i).getEventName() + "\n" + events.get(i).getDescription()));
                    dc.setStyle("-fx-background-color: #44ff44;");
                } else {
                    do {
                        if (MonthDay.from(item).equals(MonthDay.of(startTime.plusDays(7).getMonth(), startTime.plusDays(7).getDayOfMonth()))) {
                            dc.setTooltip(new Tooltip(events.get(i).getEventName() + "\n" + events.get(i).getDescription()));
                            dc.setStyle("-fx-background-color: #ffff00;");
                        }
                        startTime = startTime.plusDays(1);
                    } while (MonthDay.of(startTime.getMonth(), startTime.getDayOfMonth()).isBefore(MonthDay.of(12, 12)));
                }
            }

        }
    }
}

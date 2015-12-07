package datepickertest;


import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Otti
 */
public class CalendarEvent {
    /*int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    String eventName;
    String description;
    String type;
    LocalDateTime startDate;
    LocalDateTime endDate;
    
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getInterval() {
        return type;
    }

    public void setInterval(String interval) {
        this.type = interval;
    }

    public CalendarEvent(/*int id,*/ String eventName, String description, String type, LocalDateTime startDate, LocalDateTime endDate) {
        //this.id = id;
        this.eventName = eventName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }
    
    
    
}

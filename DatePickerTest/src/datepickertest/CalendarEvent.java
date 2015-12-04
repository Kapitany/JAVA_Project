package datepickertest;


import java.time.LocalDate;

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
    String id;
    String eventName;
    String description;
    LocalDate startDate;
    LocalDate endDate;
    String type;
    
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getInterval() {
        return type;
    }

    public void setInterval(String interval) {
        this.type = interval;
    }

    public CalendarEvent(String eventName, String description, LocalDate startDate, LocalDate endDate, String type) {
        this.eventName = eventName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        
        
    }
    
    
    
}

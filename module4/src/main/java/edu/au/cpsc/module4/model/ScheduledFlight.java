package edu.au.cpsc.module4.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;

public class ScheduledFlight implements Serializable {

    private String flightDesignator;
    private String departureAirportIdent;
    private LocalTime departureTime;
    private String arrivalAirportIdent;
    private LocalTime arrivalTime;
    private HashSet<DayOfWeek> daysOfWeek;

    public ScheduledFlight() {
        this.daysOfWeek = new HashSet<>();
    }

    //Getters and Setters

    public String getFlightDesignator() {
        return flightDesignator;
    }

    public void setFlightDesignator(String flightDesignator) {
        if (flightDesignator == null)
            throw new IllegalArgumentException("Fight Designator cannot be null");
        this.flightDesignator = flightDesignator;
    }

    public String getDepartureAirportIdent() {
        return departureAirportIdent;
    }

    public void setDepartureAirportIdent(String departureAirportIdent) {
        if (departureAirportIdent == null)
            throw new IllegalArgumentException("Departure Airport ident cannot be null");
        this.departureAirportIdent = departureAirportIdent;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        if (departureTime == null)
            throw new IllegalArgumentException("Departure time cannot be null");
        this.departureTime = departureTime;
    }

    public String getArrivalAirportIdent() {
        return arrivalAirportIdent;
    }

    public void setArrivalAirportIdent(String arrivalAirportIdent) {
        if (arrivalAirportIdent == null)
            throw new IllegalArgumentException("Arrival Airport ident cannot be null");
        this.arrivalAirportIdent = arrivalAirportIdent;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        if (arrivalTime == null)
            throw new IllegalArgumentException("Arrival Time cannot be null");
        this.arrivalTime = arrivalTime;
    }

    public HashSet<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(HashSet<DayOfWeek> daysOfWeek) {
        if (daysOfWeek == null)
            throw new IllegalArgumentException("Days of Week cannot be null");
        this.daysOfWeek = daysOfWeek;
    }
}

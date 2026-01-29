package edu.au.cpsc.module4.data;

import edu.au.cpsc.module4.model.ScheduledFlight;

import java.util.ArrayList;
import java.util.List;

public class AirlineDatabase {

    //Instance variables
    private List<ScheduledFlight> scheduledFlights;

   //Constructor
    public AirlineDatabase() {
        scheduledFlights = new ArrayList<>();
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return scheduledFlights;
    }
}

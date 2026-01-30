package edu.au.cpsc.module4;

import edu.au.cpsc.module4.data.AirlineDatabase;
import edu.au.cpsc.module4.data.AirlineDatabaseIO;
import edu.au.cpsc.module4.model.ScheduledFlight;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class AirlineDatabaseIOTest {

    private AirlineDatabase database;
    private ScheduledFlight flight1;
    private ScheduledFlight flight2;
    private File testFile;

    @Test
    void givenDatabaseWithScheduledFlightsWhenSavedAndLoadedThenAllScheduledFlightsInScheduledFlights()
            throws IOException, ClassNotFoundException {
        AirlineDatabase airlineDatabase = new AirlineDatabase();

        // Create test flight 1
        flight1 = new ScheduledFlight();
        flight1.setFlightDesignator("DL1331");
        flight1.setDepartureAirportIdent("KPIT");
        flight1.setDepartureTime(LocalTime.of(13, 30));
        flight1.setArrivalAirportIdent("KATL");
        flight1.setArrivalTime(LocalTime.of(15, 0));
        HashSet<DayOfWeek> days1 = new HashSet<>();
        days1.add(DayOfWeek.MONDAY);
        days1.add(DayOfWeek.WEDNESDAY);
        days1.add(DayOfWeek.FRIDAY);
        flight1.setDaysOfWeek(days1);

        // Create test flight 2
        flight2 = new ScheduledFlight();
        flight2.setFlightDesignator("AA100");
        flight2.setDepartureAirportIdent("KJFK");
        flight2.setDepartureTime(LocalTime.of(9, 0));
        flight2.setArrivalAirportIdent("KLAX");
        flight2.setArrivalTime(LocalTime.of(12, 0));
        HashSet<DayOfWeek> days2 = new HashSet<>();
        days2.add(DayOfWeek.TUESDAY);
        days2.add(DayOfWeek.THURSDAY);
        flight2.setDaysOfWeek(days2);

        airlineDatabase.addScheduledFlight(flight1);
        airlineDatabase.addScheduledFlight(flight2);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        AirlineDatabaseIO.save(airlineDatabase, out);

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        AirlineDatabase databaseFromStream = AirlineDatabaseIO.load(in);

        assertEquals(2, databaseFromStream.getScheduledFlights().size());
    }
}
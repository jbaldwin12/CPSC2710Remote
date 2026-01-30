package edu.au.cpsc.module4;

import edu.au.cpsc.module4.data.AirlineDatabase;
import edu.au.cpsc.module4.model.ScheduledFlight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AirlineDatabase class
 */
class AirlineDatabaseTest {

    private AirlineDatabase database;
    private ScheduledFlight flight1;
    private ScheduledFlight flight2;

    @BeforeEach
    void setUp() {
        database = new AirlineDatabase();

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
    }

    @Test
    void givenNewDatabaseThenNoScheduledFlights() {
        assertNotNull(database.getScheduledFlights());
        assertEquals(0, database.getScheduledFlights().size());
    }

    @Test
    void addScheduledFlight() {
        database.addScheduledFlight(flight1);
        assertEquals(1, database.getScheduledFlights().size());
        assertTrue(database.getScheduledFlights().contains(flight1));
    }

    @Test
    void addMultipleScheduledFlights() {
        database.addScheduledFlight(flight1);
        database.addScheduledFlight(flight2);
        assertEquals(2, database.getScheduledFlights().size());
        assertTrue(database.getScheduledFlights().contains(flight1));
        assertTrue(database.getScheduledFlights().contains(flight2));
    }

    @Test
    void removeScheduledFlight() {
        database.addScheduledFlight(flight1);
        database.addScheduledFlight(flight2);
        assertEquals(2, database.getScheduledFlights().size());

        database.removeScheduledFlight(flight1);
        assertEquals(1, database.getScheduledFlights().size());
        assertFalse(database.getScheduledFlights().contains(flight1));
        assertTrue(database.getScheduledFlights().contains(flight2));
    }

    @Test
    void removeNonExistentFlight() {
        database.addScheduledFlight(flight1);
        assertEquals(1, database.getScheduledFlights().size());

        database.removeScheduledFlight(flight2);
        assertEquals(1, database.getScheduledFlights().size());
        assertTrue(database.getScheduledFlights().contains(flight1));
    }

    @Test
    void updateScheduledFlight() {
        database.addScheduledFlight(flight1);

        // Modify flight1
        flight1.setDepartureTime(LocalTime.of(14, 30));

        // Call update (even though it does nothing in current implementation)
        database.updateScheduledFlight(flight1);

        // Verify the flight is still in the database and has the updated time
        assertEquals(1, database.getScheduledFlights().size());
        assertEquals(LocalTime.of(14, 30), database.getScheduledFlights().get(0).getDepartureTime());
    }
}
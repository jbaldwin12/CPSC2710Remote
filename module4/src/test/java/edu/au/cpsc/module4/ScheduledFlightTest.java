package edu.au.cpsc.module4;

import edu.au.cpsc.module4.model.ScheduledFlight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ScheduledFlight class
 */
class ScheduledFlightTest {

    private ScheduledFlight flight;

    @BeforeEach
    void setUp() {
        flight = new ScheduledFlight();
    }

    // Test Flight Designator
    @Test
    void testSetAndGetFlightDesignator() {
        flight.setFlightDesignator("DL1331");
        assertEquals("DL1331", flight.getFlightDesignator());
    }

    @Test
    void testSetFlightDesignatorWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            flight.setFlightDesignator(null);
        });
    }

    // Test Departure Airport Ident
    @Test
    void testSetAndGetDepartureAirportIdent() {
        flight.setDepartureAirportIdent("KPIT");
        assertEquals("KPIT", flight.getDepartureAirportIdent());
    }

    @Test
    void testSetDepartureAirportIdentWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            flight.setDepartureAirportIdent(null);
        });
    }

    // Test Departure Time
    @Test
    void testSetAndGetDepartureTime() {
        LocalTime time = LocalTime.of(13, 30); // 1:30 PM
        flight.setDepartureTime(time);
        assertEquals(time, flight.getDepartureTime());
    }

    @Test
    void testSetDepartureTimeWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            flight.setDepartureTime(null);
        });
    }

    // Test Arrival Airport Ident
    @Test
    void testSetAndGetArrivalAirportIdent() {
        flight.setArrivalAirportIdent("KATL");
        assertEquals("KATL", flight.getArrivalAirportIdent());
    }

    @Test
    void testSetArrivalAirportIdentWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            flight.setArrivalAirportIdent(null);
        });
    }

    // Test Arrival Time
    @Test
    void testSetAndGetArrivalTime() {
        LocalTime time = LocalTime.of(15, 0); // 3:00 PM
        flight.setArrivalTime(time);
        assertEquals(time, flight.getArrivalTime());
    }

    @Test
    void testSetArrivalTimeWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            flight.setArrivalTime(null);
        });
    }

    // Test Days of Week
    @Test
    void testSetAndGetDaysOfWeek() {
        HashSet<DayOfWeek> days = new HashSet<>();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.WEDNESDAY);
        days.add(DayOfWeek.FRIDAY);

        flight.setDaysOfWeek(days);
        assertEquals(3, flight.getDaysOfWeek().size());
        assertTrue(flight.getDaysOfWeek().contains(DayOfWeek.MONDAY));
        assertTrue(flight.getDaysOfWeek().contains(DayOfWeek.WEDNESDAY));
        assertTrue(flight.getDaysOfWeek().contains(DayOfWeek.FRIDAY));
        assertFalse(flight.getDaysOfWeek().contains(DayOfWeek.TUESDAY));
    }

    @Test
    void testSetDaysOfWeekWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            flight.setDaysOfWeek(null);
        });
    }

    @Test
    void testDaysOfWeekInitializedAsEmptySet() {
        assertNotNull(flight.getDaysOfWeek());
        assertEquals(0, flight.getDaysOfWeek().size());
    }

    // Integration test - create a complete flight
    @Test
    void testCompleteFlightCreation() {
        flight.setFlightDesignator("DL1331");
        flight.setDepartureAirportIdent("KPIT");
        flight.setDepartureTime(LocalTime.of(13, 30));
        flight.setArrivalAirportIdent("KATL");
        flight.setArrivalTime(LocalTime.of(15, 0));

        HashSet<DayOfWeek> days = new HashSet<>();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.TUESDAY);
        days.add(DayOfWeek.WEDNESDAY);
        days.add(DayOfWeek.THURSDAY);
        days.add(DayOfWeek.FRIDAY);
        flight.setDaysOfWeek(days);

        // Verify all fields
        assertEquals("DL1331", flight.getFlightDesignator());
        assertEquals("KPIT", flight.getDepartureAirportIdent());
        assertEquals(LocalTime.of(13, 30), flight.getDepartureTime());
        assertEquals("KATL", flight.getArrivalAirportIdent());
        assertEquals(LocalTime.of(15, 0), flight.getArrivalTime());
        assertEquals(5, flight.getDaysOfWeek().size());
    }
}
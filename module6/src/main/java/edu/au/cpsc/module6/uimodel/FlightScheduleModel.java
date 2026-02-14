/*Project name: Flight Schedule Application V2
 * Author:Jordan Baldwin
 * auburn email: jtb0185@auburn.edu
 * Date: 2/13/26
 * Description: Complete application for editing airline flight information,
 * including a file-based database so that its data is persistent between runs.
 */

package edu.au.cpsc.module6.uimodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FlightScheduleModel {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final StringProperty flightDesignatorProperty;
    private final StringProperty departureAirportProperty;
    private final StringProperty arrivalAirportProperty;
    private final StringProperty departureTimeProperty;
    private final StringProperty arrivalTimeProperty;
    private final BooleanProperty sundayProperty;
    private final BooleanProperty mondayProperty;
    private final BooleanProperty tuesdayProperty;
    private final BooleanProperty wednesdayProperty;
    private final BooleanProperty thursdayProperty;
    private final BooleanProperty fridayProperty;
    private final BooleanProperty saturdayProperty;
    private final BooleanProperty modifiedProperty;
    private final BooleanProperty newProperty;

    // Validation properties
    private final BooleanProperty flightDesignatorValidProperty;
    private final BooleanProperty departureAirportValidProperty;
    private final BooleanProperty arrivalAirportValidProperty;
    private final BooleanProperty departureTimeValidProperty;
    private final BooleanProperty arrivalTimeValidProperty;
    private final BooleanProperty validationAttemptedProperty;

    public FlightScheduleModel() {
        flightDesignatorProperty = new SimpleStringProperty();
        departureAirportProperty = new SimpleStringProperty();
        arrivalAirportProperty = new SimpleStringProperty();
        departureTimeProperty = new SimpleStringProperty();
        arrivalTimeProperty = new SimpleStringProperty();
        sundayProperty = new SimpleBooleanProperty();
        mondayProperty = new SimpleBooleanProperty();
        tuesdayProperty = new SimpleBooleanProperty();
        wednesdayProperty = new SimpleBooleanProperty();
        thursdayProperty = new SimpleBooleanProperty();
        fridayProperty = new SimpleBooleanProperty();
        saturdayProperty = new SimpleBooleanProperty();
        modifiedProperty = new SimpleBooleanProperty();
        newProperty = new SimpleBooleanProperty();

        // Initialize validation properties
        flightDesignatorValidProperty = new SimpleBooleanProperty(true);
        departureAirportValidProperty = new SimpleBooleanProperty(true);
        arrivalAirportValidProperty = new SimpleBooleanProperty(true);
        departureTimeValidProperty = new SimpleBooleanProperty(true);
        arrivalTimeValidProperty = new SimpleBooleanProperty(true);
        validationAttemptedProperty = new SimpleBooleanProperty(false);

        // Set up validation listeners
        setupValidation();

        flightDesignatorProperty.addListener((observable, oldValue, newValue) -> setModified(true));
        departureAirportProperty.addListener((observable, oldValue, newValue) -> setModified(true));
        arrivalAirportProperty.addListener((observable, oldValue, newValue) -> setModified(true));
        departureTimeProperty.addListener((observable, oldValue, newValue) -> setModified(true));
        arrivalTimeProperty.addListener((observable, oldValue, newValue) -> setModified(true));
        sundayProperty.addListener((observable, oldValue, newValue) -> setModified(true));
        mondayProperty.addListener((observable, oldValue, newValue) -> setModified(true));
        tuesdayProperty.addListener((observable, oldValue, newValue) -> setModified(true));
        wednesdayProperty.addListener((observable, oldValue, newValue) -> setModified(true));
        thursdayProperty.addListener((observable, oldValue, newValue) -> setModified(true));
        fridayProperty.addListener((observable, oldValue, newValue) -> setModified(true));
        saturdayProperty.addListener((observable, oldValue, newValue) -> setModified(true));
    }

    private void setupValidation() {
        // Validate flight designator
        flightDesignatorProperty.addListener((observable, oldValue, newValue) -> {
            boolean valid = newValue != null && !newValue.trim().isEmpty() && newValue.trim().length() <= 10;
            flightDesignatorValidProperty.set(valid);
        });

        // Validate departure airport
        departureAirportProperty.addListener((observable, oldValue, newValue) -> {
            boolean valid = newValue != null && !newValue.trim().isEmpty() && newValue.trim().length() <= 4;
            departureAirportValidProperty.set(valid);
        });

        // Validate arrival airport
        arrivalAirportProperty.addListener((observable, oldValue, newValue) -> {
            boolean valid = newValue != null && !newValue.trim().isEmpty() && newValue.trim().length() <= 4;
            arrivalAirportValidProperty.set(valid);
        });

        // Validate departure time
        departureTimeProperty.addListener((observable, oldValue, newValue) -> {
            boolean valid = isValidTime(newValue);
            departureTimeValidProperty.set(valid);
        });

        // Validate arrival time
        arrivalTimeProperty.addListener((observable, oldValue, newValue) -> {
            boolean valid = isValidTime(newValue);
            arrivalTimeValidProperty.set(valid);
        });
    }

    private boolean isValidTime(String time) {
        if (time == null || time.trim().isEmpty()) {
            return false;
        }
        try {
            LocalTime.parse(time.trim(), TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public StringProperty flightDesignatorProperty() {return flightDesignatorProperty; }
    public StringProperty departureAirportProperty() {return departureAirportProperty; }
    public StringProperty arrivalAirportProperty() {return arrivalAirportProperty; }
    public StringProperty departureTimeProperty() {return departureTimeProperty; }
    public StringProperty arrivalTimeProperty() {return arrivalTimeProperty; }
    public BooleanProperty sundayProperty() {return sundayProperty; }
    public BooleanProperty mondayProperty() {return mondayProperty; }
    public BooleanProperty tuesdayProperty() {return tuesdayProperty; }
    public BooleanProperty wednesdayProperty() {return wednesdayProperty; }
    public BooleanProperty thursdayProperty() {return thursdayProperty; }
    public BooleanProperty fridayProperty() {return fridayProperty; }
    public BooleanProperty saturdayProperty() {return saturdayProperty; }
    public BooleanProperty modifiedProperty() {return modifiedProperty; }
    public BooleanProperty newProperty() {return newProperty; }

    // Validation property getters
    public BooleanProperty flightDesignatorValidProperty() { return flightDesignatorValidProperty; }
    public BooleanProperty departureAirportValidProperty() { return departureAirportValidProperty; }
    public BooleanProperty arrivalAirportValidProperty() { return arrivalAirportValidProperty; }
    public BooleanProperty departureTimeValidProperty() { return departureTimeValidProperty; }
    public BooleanProperty arrivalTimeValidProperty() { return arrivalTimeValidProperty; }
    public BooleanProperty validationAttemptedProperty() { return validationAttemptedProperty; }

    //Convenience methods
    public String getFlightDesignator() {return flightDesignatorProperty.get(); }
    public void setFlightDesignator(String flightDesignator) { flightDesignatorProperty.set(flightDesignator); }

    public String getDepartureAirport() {return departureAirportProperty.get(); }
    public void setDepartureAirport(String departureAirport) { departureAirportProperty.set(departureAirport); }

    public String getArrivalAirport() {return arrivalAirportProperty.get(); }
    public void setArrivalAirport(String arrivalAirport) { arrivalAirportProperty.set(arrivalAirport); }

    public String getDepartureTime() {return departureTimeProperty.get(); }
    public void setDepartureTime(String departureTime) { departureTimeProperty.set(departureTime); }

    public String getArrivalTime() {return arrivalTimeProperty.get(); }
    public void setArrivalTime(String arrivalTime) { arrivalTimeProperty.set(arrivalTime); }

    public boolean getSunday() { return sundayProperty.get(); }
    public void setSunday(boolean sunday) { sundayProperty.set(sunday); }

    public boolean getMonday() { return mondayProperty.get(); }
    public void setMonday(boolean monday) { mondayProperty.set(monday); }

    public boolean getTuesday() { return tuesdayProperty.get(); }
    public void setTuesday(boolean tuesday) { tuesdayProperty.set(tuesday); }

    public boolean getWednesday() { return wednesdayProperty.get(); }
    public void setWednesday(boolean wednesday) { wednesdayProperty.set(wednesday); }

    public boolean getThursday() { return thursdayProperty.get(); }
    public void setThursday(boolean thursday) { thursdayProperty.set(thursday); }

    public boolean getFriday() { return fridayProperty.get(); }
    public void setFriday(boolean friday) { fridayProperty.set(friday); }

    public boolean getSaturday() { return saturdayProperty.get(); }
    public void setSaturday(boolean saturday) { saturdayProperty.set(saturday); }

    public boolean isModified() { return modifiedProperty.get(); }
    public void setModified(boolean m) { modifiedProperty.set(m); }

    public boolean isNew() { return newProperty.get(); }
    public void setNew(boolean n) { newProperty.set(n); }

    public boolean isValidationAttempted() { return validationAttemptedProperty.get(); }
    public void setValidationAttempted(boolean v) { validationAttemptedProperty.set(v); }
}

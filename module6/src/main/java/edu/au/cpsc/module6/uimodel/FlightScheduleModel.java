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

public class FlightScheduleModel {

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

    public void setModified(boolean m) { modifiedProperty.set(m); }
}

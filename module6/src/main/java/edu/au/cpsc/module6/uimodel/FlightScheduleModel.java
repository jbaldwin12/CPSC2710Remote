/*Project name: Flight Schedule Application V2
 * Author:Jordan Baldwin
 * auburn email: jtb0185@auburn.edu
 * Date: 2/13/26
 * Description: Complete application for editing airline flight information,
 * including a file-based database so that its data is persistent between runs.
 */

package edu.au.cpsc.module6.uimodel;

import javafx.beans.property.BooleanProperty;
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

}

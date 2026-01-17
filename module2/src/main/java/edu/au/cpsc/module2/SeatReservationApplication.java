package edu.au.cpsc.module2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class SeatReservationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        CheckBox flyingWithInfantCB = getFlyingWithInfantCB();
        CheckBox flyingWithTravelInsuranceCB = getFlyingWithTravelInsuranceCB();
        TextField flightDesignatorTF = new TextField("Flight Designator: ");
        TextField firstNameTF = new TextField("First Name: ");
        TextField lastNameTF = new TextField("Last Name: ");
        DatePicker flightDate = getFlightDate();
        VBox root = new VBox(flyingWithInfantCB, flyingWithTravelInsuranceCB, flightDesignatorTF,
                firstNameTF, lastNameTF, flightDate);
        Scene scene = new Scene(root);
        stage.setTitle("Jordan Baldwin's Seat Reservation App");
        stage.setScene(scene);
        stage.show();

    }

    private static DatePicker getFlightDate() {
        DatePicker flightDate = new DatePicker();
        flightDate.setOnAction(e -> {
            LocalDate date = flightDate.getValue();
            System.err.println("Selected date: " + date);
        });
        return flightDate;
    }

    private static CheckBox getFlyingWithInfantCB() {
        CheckBox flyingWithInfantCB = new CheckBox("Flying with infant");
        flyingWithInfantCB.setSelected(false);
        return flyingWithInfantCB;
    }

    private static CheckBox getFlyingWithTravelInsuranceCB() {
        CheckBox flyingWithTravelInsuranceCB = new CheckBox ("Flying with Travel Insurance");
        flyingWithTravelInsuranceCB.setSelected(false);
        return flyingWithTravelInsuranceCB;
    }
}

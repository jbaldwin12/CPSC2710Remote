package edu.au.cpsc.module2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

public class SeatReservationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        CheckBox flyingWithInfantCB = getFlyingWithInfantCB();
        CheckBox flyingWithTravelInsuranceCB = getFlyingWithTravelInsuranceCB();
        Label flightDesignatorLabel = new Label("Flight Designator:");
        TextField flightDesignatorTF = new TextField();
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameTF = new TextField();
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameTF = new TextField();
        DatePicker flightDate = getFlightDate();
        Label numberOfPassengersLabel = new Label("Number of Passengers:");
        TextField numberOfPassengers = new TextField("1");
        numberOfPassengers.setEditable(false);
        GridPane buttonPane = getButtonPane();
        VBox root = new VBox(flyingWithInfantCB, flyingWithTravelInsuranceCB, flightDesignatorLabel, flightDesignatorTF,
                firstNameLabel, firstNameTF, lastNameLabel, lastNameTF, flightDate, numberOfPassengersLabel,
                numberOfPassengers, buttonPane);
        Scene scene = new Scene(root, 500, 300);
        stage.setTitle("Jordan Baldwin's Seat Reservation App");
        stage.setScene(scene);
        stage.show();

    }

    private static GridPane getButtonPane() {
        GridPane buttonPane = new GridPane();
        buttonPane.add(new Button("cancel"), 0, 7);
        buttonPane.add(new Button("save"), 1, 7);
        return buttonPane;
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

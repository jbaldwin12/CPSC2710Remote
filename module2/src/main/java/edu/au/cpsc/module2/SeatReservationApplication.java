package edu.au.cpsc.module2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

public class SeatReservationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Label flyingWithInfantCBLabel = new Label("Flying with Infant");
        CheckBox flyingWithInfantCB = createFlyingWithInfantCB();

        Label flyingWithTravelInsuranceLabel = new Label("Flying with Travel Insurance");
        CheckBox flyingWithTravelInsuranceCB = createFlyingWithTravelInsuranceCB();

        Label flightDesignatorLabel = new Label("Flight Designator:");
        TextField flightDesignatorTF = new TextField();

        Label firstNameLabel = new Label("First Name:");
        TextField firstNameTF = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameTF = new TextField();

        Label flightDateLabel = new Label("Flight Date:");
        DatePicker flightDate = getFlightDate();

        Label numberOfPassengersLabel = new Label("Number of Passengers:");
        TextField numberOfPassengers = new TextField("1");
        numberOfPassengers.setEditable(false);

        GridPane layout = new GridPane();
        layout.setHgap(10);

        layout.add(flyingWithInfantCBLabel, 0,0);
        layout.add(flyingWithInfantCB,1,0 );

        layout.add(flyingWithTravelInsuranceLabel, 0,1);
        layout.add(flyingWithTravelInsuranceCB,1,1 );

        layout.add(flightDesignatorLabel, 0, 2);
        layout.add(flightDesignatorTF, 1, 2);

        layout.add(firstNameLabel, 0, 3);
        layout.add(firstNameTF, 1, 3);

        layout.add(lastNameLabel, 0, 4);
        layout.add(lastNameTF, 1, 4);

        layout.add(flightDateLabel, 0, 5);
        layout.add(flightDate, 1, 5);

        layout.add(numberOfPassengersLabel, 0, 6);
        layout.add(numberOfPassengers, 1, 6);

        Button cancelButton = new Button("Cancel");
        Button saveButton = new Button("Save");
        HBox buttonBox = new HBox(cancelButton, saveButton);
        buttonBox.setPadding(new Insets(0,0,0,10));
        buttonBox.setSpacing(10);

        BorderPane root = new BorderPane();
        root.setCenter(layout);
        root.setBottom(buttonBox);


        Scene scene = new Scene(root, 500, 300);
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

    private static CheckBox createFlyingWithInfantCB() {
        CheckBox withInfantCB = new CheckBox();
        withInfantCB.setSelected(false);
        return withInfantCB;
    }

    private static CheckBox createFlyingWithTravelInsuranceCB() {
        CheckBox withTravelInsuranceCB = new CheckBox ();
        withTravelInsuranceCB.setSelected(false);
        return withTravelInsuranceCB;
    }
}

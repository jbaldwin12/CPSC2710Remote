package edu.au.cpsc.module2;

import javafx.application.Application;
import javafx.application.Platform;
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

    private Boolean argErrorOccurred;
    private SeatReservation SeatReservation;
    private SeatReservation savedReservation;
    private CheckBox flyingWithInfantCB;
    private CheckBox flyingWithTravelInsuranceCB;
    private TextField flightDesignatorTF;
    private TextField firstNameTF;
    private TextField lastNameTF;
    private TextField numberOfPassengersTF;
    private DatePicker flightDate;
    private Button cancelButton;
    private Button saveButton;

    @Override
    public void start(Stage stage) throws IOException {
        SeatReservation = new SeatReservation();
        SeatReservation.setFirstName("Jordan");
        SeatReservation.setLastName("Baldwin");
        SeatReservation.setFlightDate(LocalDate.now());
        SeatReservation.setFlightDesignator("AA1001");

        Label flyingWithInfantCBLabel = new Label("Flying with Infant");
        flyingWithInfantCB = createFlyingWithInfantCB();

        Label flyingWithTravelInsuranceLabel = new Label("Flying with Travel Insurance");
        flyingWithTravelInsuranceCB = createFlyingWithTravelInsuranceCB();

        Label flightDesignatorLabel = new Label("Flight Designator:");
        flightDesignatorTF = new TextField();

        Label firstNameLabel = new Label("First Name:");
        firstNameTF = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        lastNameTF = new TextField();

        Label flightDateLabel = new Label("Flight Date:");
        flightDate = getFlightDate();

        Label numberOfPassengersLabel = new Label("Number of Passengers:");
        numberOfPassengersTF = new TextField("1");
        numberOfPassengersTF.setEditable(false);

        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setPadding(new Insets(0,0,0,10));

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
        layout.add(numberOfPassengersTF, 1, 6);

        cancelButton = createCancelButton();
        saveButton = createSaveButton();
        HBox buttonBox = new HBox(cancelButton, saveButton);
        buttonBox.setPadding(new Insets(0,0,10,10));
        buttonBox.setSpacing(10);

        BorderPane root = new BorderPane();
        root.setCenter(layout);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 500, 300);
        stage.setTitle("Jordan Baldwin's Seat Reservation App");
        stage.setScene(scene);

        updateUI();

        stage.show();
    }

    private void updateUI() {
        flightDesignatorTF.setText(SeatReservation.getFlightDesignator());
        firstNameTF.setText(SeatReservation.getFirstName());
        lastNameTF.setText(SeatReservation.getLastName());
        flightDate.setValue(SeatReservation.getFlightDate());
    }

    private static DatePicker getFlightDate() {
        DatePicker fDate = new DatePicker();
        fDate.setOnAction(e -> {
            LocalDate date = fDate.getValue();
            System.err.println("Selected date: " + date);
        });
        return fDate;
    }

    private CheckBox createFlyingWithInfantCB() {
        CheckBox withInfantCB = new CheckBox();
        withInfantCB.setSelected(false);
        withInfantCB.setOnAction(e -> {
            if (withInfantCB.isSelected()) {
                numberOfPassengersTF.setText("2");
            } else {
                numberOfPassengersTF.setText("1");
            }
        });
        return withInfantCB;
    }

    private CheckBox createFlyingWithTravelInsuranceCB() {
        CheckBox withTravelInsuranceCB = new CheckBox ();
        withTravelInsuranceCB.setSelected(false);
        return withTravelInsuranceCB;
    }

    private Button createSaveButton() {
        Button save = new Button("Save");

        save.setOnAction(e -> {
            savedReservation = SeatReservation;
            try {
                argErrorOccurred = false;
                updateSeatReservation();
            } catch (Exception IllegalArgumentException) {
                argErrorOccurred = true;
                System.out.println("Illegal Argument Exception Occurred");
            }

            if (argErrorOccurred) {
                SeatReservation = savedReservation;
            } else {
                System.out.println("First Name                   = " + SeatReservation.getFirstName());
                System.out.println("Last Name                    = " + SeatReservation.getLastName());
                System.out.println("Flight Date                  = " + SeatReservation.getFlightDate());
                System.out.println("Flight Designator            = " + SeatReservation.getFlightDesignator());
                System.out.println("Flying With Infant           = " + (SeatReservation.isFlyingWithInfant() ? "True" : "False"));
                System.out.println("Flying With Travel Insurance = " + (SeatReservation.hasTravelInsurance() ? "True" : "False"));

                Platform.exit();
            }
        });

        return save;
    }

    private Button createCancelButton() {
        Button cancel = new Button("Cancel");

        cancel.setOnAction(e -> {
            System.out.println("Cancel clicked");
            Platform.exit();
        });

        return cancel;
    }

    private void updateSeatReservation() {
        SeatReservation.setFirstName(firstNameTF.getText());
        SeatReservation.setLastName(lastNameTF.getText());
        SeatReservation.setFlightDate(flightDate.getValue());
        SeatReservation.setFlightDesignator(flightDesignatorTF.getText());
        if (flyingWithInfantCB.isSelected()) {
            SeatReservation.makeFlyingWithInfant();
        } else {
            SeatReservation.makeNotFlyingWithInfant();
        }
        if (flyingWithTravelInsuranceCB.isSelected()) {
            SeatReservation.makeFlyingWithTravelInsurance();
        } else {
            SeatReservation.makeNotFlyingWithTravelInsurance();
        }
    }
}

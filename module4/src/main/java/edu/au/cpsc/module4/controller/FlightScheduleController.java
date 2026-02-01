package edu.au.cpsc.module4.controller;

import edu.au.cpsc.module4.data.AirlineDatabase;
import edu.au.cpsc.module4.data.Db;
import edu.au.cpsc.module4.model.ScheduledFlight;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;

public class FlightScheduleController {

    @FXML
    private TableView<ScheduledFlight> flightTable;
    @FXML
    private TableColumn<ScheduledFlight, String> flightDesignatorColumn;
    @FXML
    private TableColumn<ScheduledFlight, String> departureAirportColumn;
    @FXML
    private TableColumn<ScheduledFlight, String> arrivalAirportColumn;
    @FXML
    private TableColumn<ScheduledFlight, String> departureTimeColumn;
    @FXML
    private TableColumn<ScheduledFlight, String> arrivalTimeColumn;
    @FXML
    private TableColumn<ScheduledFlight, String> daysOfWeekColumn;

    @FXML
    private TextField flightDesignatorField;
    @FXML
    private TextField departureAirportField;
    @FXML
    private TextField departureTimeField;
    @FXML
    private TextField arrivalAirportField;
    @FXML
    private TextField arrivalTimeField;

    @FXML
    private ToggleButton mondayToggle;
    @FXML
    private ToggleButton tuesdayToggle;
    @FXML
    private ToggleButton wednesdayToggle;
    @FXML
    private ToggleButton thursdayToggle;
    @FXML
    private ToggleButton fridayToggle;
    @FXML
    private ToggleButton saturdayToggle;
    @FXML
    private ToggleButton sundayToggle;

    @FXML
    private Button addButton;
    @FXML
    private Button newButton;
    @FXML
    private Button deleteButton;

    private AirlineDatabase database;
    private ObservableList<ScheduledFlight> flightList;
    private ScheduledFlight selectedFlight;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @FXML
    public void initialize() {
        // Load database
        database = Db.getDatabase();
        flightList = FXCollections.observableArrayList(database.getScheduledFlights());

        // Set up table columns
        setupTableColumns();

        // Populate table
        flightTable.setItems(flightList);

        // Set up table selection listener
        flightTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectedFlight = newValue;
                        populateFields(newValue);
                        addButton.setText("Update");
                    } else {
                        selectedFlight = null;
                        addButton.setText("Add");
                    }
                }
        );
    }

    private void setupTableColumns() {
        flightDesignatorColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFlightDesignator()));

        departureAirportColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDepartureAirportIdent()));

        arrivalAirportColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getArrivalAirportIdent()));

        departureTimeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDepartureTime().format(TIME_FORMATTER)));

        arrivalTimeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getArrivalTime().format(TIME_FORMATTER)));

        daysOfWeekColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(formatDaysOfWeek(cellData.getValue().getDaysOfWeek())));
    }

    private String formatDaysOfWeek(HashSet<DayOfWeek> days) {
        StringBuilder sb = new StringBuilder();
        if (days.contains(DayOfWeek.MONDAY)) sb.append("M");
        if (days.contains(DayOfWeek.TUESDAY)) sb.append("T");
        if (days.contains(DayOfWeek.WEDNESDAY)) sb.append("W");
        if (days.contains(DayOfWeek.THURSDAY)) sb.append("R");
        if (days.contains(DayOfWeek.FRIDAY)) sb.append("F");
        if (days.contains(DayOfWeek.SATURDAY)) sb.append("S");
        if (days.contains(DayOfWeek.SUNDAY)) sb.append("U");
        return sb.toString();
    }

    private void populateFields(ScheduledFlight flight) {
        flightDesignatorField.setText(flight.getFlightDesignator());
        departureAirportField.setText(flight.getDepartureAirportIdent());
        departureTimeField.setText(flight.getDepartureTime().format(TIME_FORMATTER));
        arrivalAirportField.setText(flight.getArrivalAirportIdent());
        arrivalTimeField.setText(flight.getArrivalTime().format(TIME_FORMATTER));

        // Set toggle buttons
        HashSet<DayOfWeek> days = flight.getDaysOfWeek();
        mondayToggle.setSelected(days.contains(DayOfWeek.MONDAY));
        tuesdayToggle.setSelected(days.contains(DayOfWeek.TUESDAY));
        wednesdayToggle.setSelected(days.contains(DayOfWeek.WEDNESDAY));
        thursdayToggle.setSelected(days.contains(DayOfWeek.THURSDAY));
        fridayToggle.setSelected(days.contains(DayOfWeek.FRIDAY));
        saturdayToggle.setSelected(days.contains(DayOfWeek.SATURDAY));
        sundayToggle.setSelected(days.contains(DayOfWeek.SUNDAY));
    }

    private void clearFields() {
        flightDesignatorField.clear();
        departureAirportField.clear();
        departureTimeField.clear();
        arrivalAirportField.clear();
        arrivalTimeField.clear();

        mondayToggle.setSelected(false);
        tuesdayToggle.setSelected(false);
        wednesdayToggle.setSelected(false);
        thursdayToggle.setSelected(false);
        fridayToggle.setSelected(false);
        saturdayToggle.setSelected(false);
        sundayToggle.setSelected(false);
    }

    private ScheduledFlight createFlightFromFields() throws IllegalArgumentException {
        if (flightDesignatorField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Flight designator is required");
        }
        if (departureAirportField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Departure airport is required");
        }
        if (arrivalAirportField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Arrival airport is required");
        }

        ScheduledFlight flight = new ScheduledFlight();

        flight.setFlightDesignator(flightDesignatorField.getText().trim());
        flight.setDepartureAirportIdent(departureAirportField.getText().trim());
        flight.setArrivalAirportIdent(arrivalAirportField.getText().trim());

        try {
            LocalTime depTime = LocalTime.parse(departureTimeField.getText().trim(), TIME_FORMATTER);
            flight.setDepartureTime(depTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid departure time format. Use HH:MM");
        }

        try {
            LocalTime arrTime = LocalTime.parse(arrivalTimeField.getText().trim(), TIME_FORMATTER);
            flight.setArrivalTime(arrTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid arrival time format. Use HH:MM");
        }

        // Get days of week from toggle buttons
        HashSet<DayOfWeek> days = new HashSet<>();
        if (mondayToggle.isSelected()) days.add(DayOfWeek.MONDAY);
        if (tuesdayToggle.isSelected()) days.add(DayOfWeek.TUESDAY);
        if (wednesdayToggle.isSelected()) days.add(DayOfWeek.WEDNESDAY);
        if (thursdayToggle.isSelected()) days.add(DayOfWeek.THURSDAY);
        if (fridayToggle.isSelected()) days.add(DayOfWeek.FRIDAY);
        if (saturdayToggle.isSelected()) days.add(DayOfWeek.SATURDAY);
        if (sundayToggle.isSelected()) days.add(DayOfWeek.SUNDAY);

        flight.setDaysOfWeek(days);

        return flight;
    }

    @FXML
    protected void addButtonAction() {
        try {
            ScheduledFlight flight = createFlightFromFields();

            if (selectedFlight != null) {
                // Update existing flight
                database.removeScheduledFlight(selectedFlight);
                database.addScheduledFlight(flight);
                flightList.remove(selectedFlight);
                flightList.add(flight);
            } else {
                // Add new flight
                database.addScheduledFlight(flight);
                flightList.add(flight);
            }

            Db.saveDatabase();
            flightTable.refresh();
            newButtonAction();

        } catch (IllegalArgumentException e) {
            showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    protected void newButtonAction() {
        clearFields();
        flightTable.getSelectionModel().clearSelection();
        selectedFlight = null;
    }

    @FXML
    protected void deleteButtonAction() {
        if (selectedFlight != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Delete");
            confirm.setHeaderText("Delete Flight");
            confirm.setContentText("Are you sure you want to delete flight " +
                    selectedFlight.getFlightDesignator() + "?");

            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    database.removeScheduledFlight(selectedFlight);
                    flightList.remove(selectedFlight);
                    Db.saveDatabase();
                    newButtonAction();
                }
            });
        } else {
            showAlert("No Selection", "Please select a flight to delete", Alert.AlertType.WARNING);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    protected void closeMenuAction() {
        Platform.exit();
    }
}
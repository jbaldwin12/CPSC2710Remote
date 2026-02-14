/*Project name: Flight Schedule Application V2
 * Author:Jordan Baldwin
 * auburn email: jtb0185@auburn.edu
 * Date: 2/13/26
 * Description: Complete application for editing airline flight information,
 * including a file-based database so that its data is persistent between runs.
 */

package edu.au.cpsc.module6.controller;

import edu.au.cpsc.module6.data.AirlineDatabase;
import edu.au.cpsc.module6.data.Db;
import edu.au.cpsc.module6.model.ScheduledFlight;
import edu.au.cpsc.module6.uimodel.FlightScheduleModel;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
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
    private FlightScheduleModel model;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @FXML
    public void initialize() {
        // Load database
        database = Db.getDatabase();
        flightList = FXCollections.observableArrayList(database.getScheduledFlights());

        // Set up UI model
        model = new FlightScheduleModel();
        model.setNew(true);
        setupButtonBindings();
        setupFieldBindings();
        setupValidation();

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
                        model.setModified(false);
                        model.setNew(false);
                    } else {
                        selectedFlight = null;
                        model.setNew(true);
                    }
                }
        );
    }

    private void setupValidation() {
        model.flightDesignatorValidProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && model.getFlightDesignator() != null && !model.getFlightDesignator().isEmpty()) {
                flightDesignatorField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            } else {
                flightDesignatorField.setStyle("");
            }
        });

        model.departureAirportValidProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && model.getDepartureAirport() != null && !model.getDepartureAirport().isEmpty()) {
                departureAirportField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            } else {
                departureAirportField.setStyle("");
            }
        });

        model.arrivalAirportValidProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && model.getArrivalAirport() != null && !model.getArrivalAirport().isEmpty()) {
                arrivalAirportField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            } else {
                arrivalAirportField.setStyle("");
            }
        });

        model.departureTimeValidProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && model.getDepartureTime() != null && !model.getDepartureTime().isEmpty()) {
                departureTimeField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            } else {
                departureTimeField.setStyle("");
            }
        });

        model.arrivalTimeValidProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && model.getArrivalTime() != null && !model.getArrivalTime().isEmpty()) {
                arrivalTimeField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            } else {
                arrivalTimeField.setStyle("");
            }
        });
    }

    private void setupButtonBindings() {
        StringBinding labelProperty = Bindings.when(model.newProperty())
                .then("Add")
                .otherwise("Update");
        addButton.textProperty().bind(labelProperty);

        addButton.disableProperty().bind(model.modifiedProperty().not());
        newButton.disableProperty().bind(model.modifiedProperty().or(model.newProperty()));
        deleteButton.disableProperty().bind(model.modifiedProperty().or(model.newProperty()));
    }

    private void setupFieldBindings() {
        flightDesignatorField.textProperty().bindBidirectional(model.flightDesignatorProperty());
        departureAirportField.textProperty().bindBidirectional(model.departureAirportProperty());
        arrivalAirportField.textProperty().bindBidirectional(model.arrivalAirportProperty());
        departureTimeField.textProperty().bindBidirectional(model.departureTimeProperty());
        arrivalTimeField.textProperty().bindBidirectional(model.arrivalTimeProperty());
        mondayToggle.selectedProperty().bindBidirectional(model.mondayProperty());
        tuesdayToggle.selectedProperty().bindBidirectional(model.tuesdayProperty());
        wednesdayToggle.selectedProperty().bindBidirectional(model.wednesdayProperty());
        thursdayToggle.selectedProperty().bindBidirectional(model.thursdayProperty());
        fridayToggle.selectedProperty().bindBidirectional(model.fridayProperty());
        saturdayToggle.selectedProperty().bindBidirectional(model.saturdayProperty());
        sundayToggle.selectedProperty().bindBidirectional(model.sundayProperty());
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
        model.setFlightDesignator(flight.getFlightDesignator());
        model.setDepartureAirport(flight.getDepartureAirportIdent());
        model.setDepartureTime(flight.getDepartureTime().format(TIME_FORMATTER));
        model.setArrivalAirport(flight.getArrivalAirportIdent());
        model.setArrivalTime(flight.getArrivalTime().format(TIME_FORMATTER));

        // Set toggle buttons
        HashSet<DayOfWeek> days = flight.getDaysOfWeek();
        model.setMonday(days.contains(DayOfWeek.MONDAY));
        model.setTuesday(days.contains(DayOfWeek.TUESDAY));
        model.setWednesday(days.contains(DayOfWeek.WEDNESDAY));
        model.setThursday(days.contains(DayOfWeek.THURSDAY));
        model.setFriday(days.contains(DayOfWeek.FRIDAY));
        model.setSaturday(days.contains(DayOfWeek.SATURDAY));
        model.setSunday(days.contains(DayOfWeek.SUNDAY));
    }

    private void clearFields() {
            model.setFlightDesignator("");
            model.setDepartureAirport("");
            model.setDepartureTime("");
            model.setArrivalAirport("");
            model.setArrivalTime("");

            model.setMonday(false);
            model.setTuesday(false);
            model.setWednesday(false);
            model.setThursday(false);
            model.setFriday(false);
            model.setSaturday(false);
            model.setSunday(false);
    }

    private ScheduledFlight createFlightFromFields() throws IllegalArgumentException {
        // Get values from model (handle nulls)
        String flightDes = model.getFlightDesignator() != null ? model.getFlightDesignator() : "";
        String depAirport = model.getDepartureAirport() != null ? model.getDepartureAirport() : "";
        String arrAirport = model.getArrivalAirport() != null ? model.getArrivalAirport() : "";
        String depTime = model.getDepartureTime() != null ? model.getDepartureTime() : "";
        String arrTime = model.getArrivalTime() != null ? model.getArrivalTime() : "";

        if (flightDes.trim().isEmpty()) {
            throw new IllegalArgumentException("Flight designator is required");
        }
        if (depAirport.trim().isEmpty()) {
            throw new IllegalArgumentException("Departure airport is required");
        }
        if (arrAirport.trim().isEmpty()) {
            throw new IllegalArgumentException("Arrival airport is required");
        }

        ScheduledFlight flight = new ScheduledFlight();

        flight.setFlightDesignator(flightDes.trim());
        flight.setDepartureAirportIdent(depAirport.trim());
        flight.setArrivalAirportIdent(arrAirport.trim());

        try {
            LocalTime depTimeObj = LocalTime.parse(depTime.trim(), TIME_FORMATTER);
            flight.setDepartureTime(depTimeObj);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid departure time format. Use HH:MM");
        }

        try {
            LocalTime arrTimeObj = LocalTime.parse(arrTime.trim(), TIME_FORMATTER);
            flight.setArrivalTime(arrTimeObj);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid arrival time format. Use HH:MM");
        }

        // Get days of week from model
        HashSet<DayOfWeek> days = new HashSet<>();
        if (model.getMonday()) days.add(DayOfWeek.MONDAY);
        if (model.getTuesday()) days.add(DayOfWeek.TUESDAY);
        if (model.getWednesday()) days.add(DayOfWeek.WEDNESDAY);
        if (model.getThursday()) days.add(DayOfWeek.THURSDAY);
        if (model.getFriday()) days.add(DayOfWeek.FRIDAY);
        if (model.getSaturday()) days.add(DayOfWeek.SATURDAY);
        if (model.getSunday()) days.add(DayOfWeek.SUNDAY);

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
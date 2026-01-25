package edu.au.cpsc.module3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

import java.util.List;

public class AirportController {
    @FXML
    private TextField identField;
    @FXML
    private TextField iataCodeField;
    @FXML
    private TextField localCodeField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField elevationField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField regionField;
    @FXML
    private TextField municipalityField;
    @FXML
    private Button searchButton;
    @FXML
    private WebView webView;
    public void initialize() {
        // Get the engine from the WebView
        WebEngine engine = webView.getEngine();

        // Load the URL for Windy at your coordinates
        engine.load("https://www.windy.com/?40.49150085,-80.23290253,12");
    }

    private List<Airport> airPortList;

    public void setAirportList(List<Airport> aList) {
        this.airPortList = aList;
    }

    @FXML
    private void handleSearch() {
        // Read values from 3 searchable fields
        String ident = (identField != null ? identField.getText().trim() : "");
        String iataCode = (iataCodeField != null ? iataCodeField.getText().trim() : "");
        String localCode = (localCodeField != null ? localCodeField.getText().trim() : "");

        System.out.println("ident = " + ident);
        System.out.println("iata = " + iataCode);
        System.out.println("local = " + localCode);

        // Search Airport list based on first non-blank searchable field
        Airport foundAirport = null;

        if (!ident.isBlank()) {
            System.out.println("Searching on ident = " + ident);
            foundAirport = searchByIdent(ident);
        } else if (!iataCode.isBlank()) {
            System.out.println("Searching on iataCode = " + iataCode);
            foundAirport = searchByIata(iataCode);
        } else if (!localCode.isBlank()) {
            System.out.println("Searching on localCode = " + localCode);
            foundAirport = searchByLocalCode(localCode);
        } else {
            System.out.println("No search criteria entered!");
        }

        // Display the found airport (or show not found message)
        if (foundAirport != null) {
            displayAirport(foundAirport);
        } else {
            System.out.println("Airport not found!");
            clearFields();
        }
    }

    private Airport searchByIdent(String ident) {
        for (Airport airport : airPortList) {
            if (airport.getIdent().equalsIgnoreCase(ident)) {
                return airport;
            }
        }
        return null;
    }

    private Airport searchByIata(String iata) {
        for (Airport airport : airPortList) {
            if (airport.getIataCode().equalsIgnoreCase(iata)) {
                return airport;
            }
        }
        return null;
    }

    private Airport searchByLocalCode(String localCode) {
        for (Airport airport : airPortList) {
            if (airport.getLocalCode().equalsIgnoreCase(localCode)) {
                return airport;
            }
        }
        return null;
    }

    private void displayAirport(Airport airport) {
        typeField.setText(airport.getType());
        nameField.setText(airport.getName());
        elevationField.setText(String.valueOf(airport.getElevationFt()));
        countryField.setText(airport.getIsoCountry());
        regionField.setText(airport.getIsoRegion());
        municipalityField.setText(airport.getMunicipality());
    }

    private void clearFields() {
        typeField.setText("");
        nameField.setText("");
        elevationField.setText("");
        countryField.setText("");
        regionField.setText("");
        municipalityField.setText("");
    }
}
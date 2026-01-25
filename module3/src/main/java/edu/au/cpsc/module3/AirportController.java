package edu.au.cpsc.module3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AirportController {
    @FXML
    private TextField identField;
    @FXML
    private TextField iataField;
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
    private void handleSearchButton() {
        System.out.println("Search button clicked!");
        // Your search logic will go here later
    }

    @FXML
    private void handleSearchText() {
        System.out.println("Search text entered!");
        // Your search logic will go here later
    }

}
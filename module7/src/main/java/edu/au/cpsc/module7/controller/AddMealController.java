package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.model.Meal;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddMealController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField mealNameField;

    @FXML
    private TextField caloriesField;

    @FXML
    private TextField proteinField;

    @FXML
    private TextArea notesArea;

    private MainWindowController mainController;

    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
    }

    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleSave() {
        try {
            LocalDate date = datePicker.getValue();
            String name = mealNameField.getText();
            int calories = Integer.parseInt(caloriesField.getText());
            int protein = Integer.parseInt(proteinField.getText());

            if (name == null || name.trim().isEmpty()) {
                showError("Please enter a meal name.");
                return;
            }

            Meal meal = new Meal(date, name, calories, protein);
            meal.setNotes(notesArea.getText());

            mainController.addMeal(meal);

            // Close window
            Stage stage = (Stage) datePicker.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            showError("Please enter valid numbers for calories and protein.");
        }
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) datePicker.getScene().getWindow();
        stage.close();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

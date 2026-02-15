package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.model.Meal;
import edu.au.cpsc.module7.model.NutritionGoals;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class MainWindowController {

    @FXML
    private TableView<Meal> mealTable;

    @FXML
    private TableColumn<Meal, LocalDate> dateColumn;

    @FXML
    private TableColumn<Meal, String> nameColumn;

    @FXML
    private TableColumn<Meal, Integer> caloriesColumn;

    @FXML
    private TableColumn<Meal, Integer> proteinColumn;

    @FXML
    private TableColumn<Meal, String> notesColumn;

    @FXML
    private Label totalCaloriesLabel;

    @FXML
    private Label totalProteinLabel;

    private ObservableList<Meal> meals = FXCollections.observableArrayList();
    private NutritionGoals goals = new NutritionGoals();

    @FXML
    public void initialize() {
        // Set up table columns
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));
        proteinColumn.setCellValueFactory(new PropertyValueFactory<>("protein"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        mealTable.setItems(meals);

        updateSummary();
    }

    private void updateSummary() {
        // Calculate today's totals
        LocalDate today = LocalDate.now();
        int totalCalories = meals.stream()
                .filter(m -> m.getDate().equals(today))
                .mapToInt(Meal::getCalories)
                .sum();

        int totalProtein = meals.stream()
                .filter(m -> m.getDate().equals(today))
                .mapToInt(Meal::getProtein)
                .sum();

        // Update labels
        totalCaloriesLabel.setText(totalCalories + " / " + goals.getCalGoal() + " cal");
        totalProteinLabel.setText(totalProtein + " / " + goals.getProteinGoal() + "g");
    }

    @FXML
    private void handleDeleteMeal() {
        Meal selected = mealTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            meals.remove(selected);
            updateSummary();
        } else {
            showError("Please select a meal to delete.");
        }
    }

    @FXML
    private void handleAddMeal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/au/cpsc/module7/AddMealWindow.fxml"));
            Parent root = loader.load();

            AddMealController controller = loader.getController();
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Add Meal");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(getClass().getResource("/edu/au/cpsc/module7/style.css").toExternalForm());
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            showError("Error opening Add Meal window:" + e.getMessage());
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Nutrition Tracker");
        alert.setHeaderText("Nutrition Tracker");
        alert.setContentText("Version 1.0\n\nTrack your daily meals and nutrition goals!\n\nCreated for personal nutrition tracking.");
        alert.showAndWait();
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
        updateSummary();
    }

    public void updateGoals(NutritionGoals newGoals) {
        this.goals = newGoals;
        updateSummary();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
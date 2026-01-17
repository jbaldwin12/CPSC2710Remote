package edu.au.cpsc.module2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SeatReservationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        CheckBox flyingWithInfantCB = new CheckBox("Flying with infant");
        flyingWithInfantCB.setSelected(true);
        VBox root = new VBox(flyingWithInfantCB);
        Scene scene = new Scene(root);
        stage.setTitle("Jordan Baldwin's Seat Reservation App");
        stage.setScene(scene);
        stage.show();

    }
}

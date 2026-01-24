package edu.au.cpsc.module3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static edu.au.cpsc.module3.Airport.printAirports;

public class AirportApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Read Airport list from file
        try {
            List<Airport> airportList = Airport.readAll();
            System.out.printf("Records = %-10d%n", airportList.size());
            //printAirports(airportList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(AirportApplication.class.getResource("airport-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}

package edu.au.cpsc.module3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static edu.au.cpsc.module3.Airport.printAirports;

public class AirportApplication extends Application {
    private List<Airport> airPortList;

    @Override
    public void start(Stage stage) throws IOException {

        // Load Scene Builder FXML
        FXMLLoader fxmlLoader = new FXMLLoader(AirportApplication.class.getResource("airport-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);

        // Read Airport list from file and pass
        try {
            airPortList = Airport.readAll();
            System.out.printf("Records = %-10d%n", airPortList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        AirportController controller = fxmlLoader.getController();
        controller.setAirportList(airPortList);

        // Display UI Window
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }
}

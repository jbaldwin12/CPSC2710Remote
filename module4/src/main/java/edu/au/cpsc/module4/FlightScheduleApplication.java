/*Project name: Flight Schedule Application
* Author:Jordan Baldwin
* auburn email: jtb0185@auburn.edu
* Date: 1/27/26
* Description: Complete application for editing airline flight information,
* including a file-based database so that its data is persistent between runs.
*/

        package edu.au.cpsc.module4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FlightScheduleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FlightScheduleApplication.class.getResource("flight-schedule-app.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Jordan Baldwin's Flight Designator App");
        stage.setScene(scene);
        stage.show();
    }
}

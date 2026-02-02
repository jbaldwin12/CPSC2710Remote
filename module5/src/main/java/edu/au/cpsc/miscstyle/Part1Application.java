/* Project name: Style Sheet to Apply Style Info.
* Author: Jordan Baldwin
* auburn email: jtb0185@auburn.edu
* Date: 2/2/26
* Description: A style sheet to apply style information to an application that has been provided.
 */

        package edu.au.cpsc.miscstyle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Part1Application extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Part1Application.class.getResource("part1.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Jordan Baldwin's Flight Designator App");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
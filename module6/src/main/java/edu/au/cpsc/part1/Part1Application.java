/* Project Name: Practice with properties and bindings
 * Author: Jordan Baldwin
 * Auburn Email: jtb0185@auburn.edu
 * Date:2/12/26
 * Description: familiarize use of bindings
 */

package edu.au.cpsc.part1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Part1Application extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Part1Application.class.getResource("part1-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Jordan Baldwin's Flight Part1 App");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
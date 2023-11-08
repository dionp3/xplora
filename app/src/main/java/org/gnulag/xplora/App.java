package org.gnulag.xplora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.gnulag.xplora.controllers.Controller;

public class App extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    // Create a label with the "Hello World!" text
    try {
      FXMLLoader board = new FXMLLoader(App.class.getResource("/fxml/main.fxml"));
      Parent root = board.load();
      Controller controller = board.getController();
      // Create a scene and set it on the stage
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);

      // Set the title of the application window
      primaryStage.setTitle("Xplora");

      // Show the application window
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}

package org.gnulag.xplora;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    // Create a label with the "Hello World!" text
    try {
      FXMLLoader board = new FXMLLoader(App.class.getResource("/fxml/board.fxml"));
      Parent root = board.load();
      // Create a scene and set it on the stage
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);

      // Set the title of the application window
      primaryStage.setTitle("xplora");

      // Show the application window
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    // Launch the JavaFX application
    launch(args);
  }
}

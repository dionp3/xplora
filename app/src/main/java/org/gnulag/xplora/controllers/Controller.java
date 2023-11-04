package org.gnulag.xplora.controllers;

/**
 * Controller
 */

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controller {
  @FXML
  private TextArea textArea;

  @FXML
  private VBox textAreaContainer;
  @FXML
  private TextField inputTextField;

  private final String[] textData = {
        "Generator nomor acak",
        "arti kata acak",
        "pemilih nama acak",
        "apa itu random",
        "random boolean dalam binary",
        "orang random yang tertangkap kamera"
    };

    public void showTextInTextArea() {
        String inputText = inputTextField.getText();
        textAreaContainer.getChildren().clear();

        for (String text : textData) {
            if (text.toLowerCase().contains(inputText.toLowerCase())) {
                TextArea resultTextArea = new TextArea(text);
                resultTextArea.setWrapText(true);
                resultTextArea.setPrefWidth(400); // Sesuaikan lebar sesuai kebutuhan
                textAreaContainer.getChildren().add(resultTextArea);
            }
        }

        inputTextField.clear();
    }

  // public void showTextInTextArea() {
  //   String inputText = inputTextField.getText();
  //   createNewTextArea(inputText);
  //   inputTextField.clear();
  // }

  // private void createNewTextArea(String text) {
  //   TextArea newTextArea = new TextArea(text);
  //   newTextArea.setWrapText(true);
  //   textAreaContainer.getChildren().add(newTextArea);
  // }

  // public void iterateThroughTextArea() {
  //   for (int i = 0; i < textAreaContainer.getChildren().size(); i++) {
  //     TextArea textArea = (TextArea) textAreaContainer.getChildren().get(i);
  //     String text = textArea.getText();
  //     System.out.println("TextArea " + (i + 1) + ": " + text);
  //   }
  // }
}
  // public void showTextInTextArea() {
  //       String inputText = inputTextField.getText();

  //       // Buat tiga baris teks yang berbeda
  //       String text1 = inputText;
  //       String text2 = inputText + " ribu";
  //       String text3 = inputText + " merupakan bilangan";

  //       // Tambahkan tiga baris teks ke TextArea
  //       textArea.appendText(text1 + "\n" + text2 + "\n" + text3 + "\n");

  //       // Bersihkan TextField
  //       inputTextField.clear();
  //   }

  //   public void iterateThroughTextArea() {
  //       String text = textArea.getText();
  //       String[] lines = text.split("\n");
  //       for (String line : lines) {
  //           System.out.println(line);
  //       }
  //   }


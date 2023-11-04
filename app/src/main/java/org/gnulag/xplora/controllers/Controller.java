package org.gnulag.xplora.controllers;

/**
 * Controller
 */

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.web.WebView;
public class Controller {
  @FXML
  private TextArea textArea;

  @FXML
  private VBox textAreaContainer;
  @FXML
  private TextField inputTextField;
  
  private Map<String, String> data = new HashMap<>();

    public Controller() {
        // Menambahkan data ke kamus
        data.put("Generator nomor acak", "ini menghasilkan angka acak kriptografis yang cocok untuk sebagian ...");
        data.put("arti kata acak", "acak 1 1 a tanpa pola; sebarang: responden kuis itu diambil secara --;");
        data.put("pemilih nama acak", "dalam pilihan pemilu ini akan dipilih namanya secara acak ...");
        data.put("apa itu random", "Dikutip dari Vocabulary, random adalah adjective atau kata sifat yang u ...");
        data.put("random boolean dalam binary", "A few options: one-of [ true false ].  ...");
        data.put("orang random yang tertangkap kamera", "Nah, sepuluh orang dibawah ini yang tertangkap kamera ...");
    }

    public void showTextInTextArea() {
        String inputText = inputTextField.getText();
        textAreaContainer.getChildren().clear();

        for (String key : data.keySet()) {
            String value = data.get(key);
            String formattedKey = key.replace(inputText, "<b>" + inputText + "</b>");
            String formattedValue = value.replace(inputText, "<b>" + inputText + "</b>");
            if (key.toLowerCase().contains(inputText.toLowerCase()) || value.toLowerCase().contains(inputText.toLowerCase())) {
                WebView webView = new WebView();
                String content = "judul: " + formattedKey + "<br>isi konten: " + formattedValue;
                webView.getEngine().loadContent(content);
                textAreaContainer.getChildren().add(webView);
                // TextArea resultTextArea = new TextArea("judul: " + key + "\nisi konten: " + value);
                // resultTextArea.setWrapText(true);
                // resultTextArea.setPrefWidth(700); // Sesuaikan lebar sesuai kebutuhan
                // textAreaContainer.getChildren().add(resultTextArea);
            }
        }

        inputTextField.clear();
    }
}

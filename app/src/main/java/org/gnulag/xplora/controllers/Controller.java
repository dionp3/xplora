package org.gnulag.xplora.controllers;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.gnulag.xplora.utils.PrintsUtil;
import org.gnulag.xplora.models.RedBlackTreeMap;
import org.gnulag.xplora.utils.JsonUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.web.WebView;
public class Controller implements Initializable {

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField searchBar;

    @FXML
    private Label searchButton;

    @FXML
    private AnchorPane slider;
    
    @FXML
    private VBox textAreaContainer;
    @FXML
    private Label backButton;

    @FXML
    private TextArea description;

    private RedBlackTreeMap<String, String> rbTree;

    public Controller() {
        rbTree = new RedBlackTreeMap<>();
        JsonUtil.loadJsonData(rbTree, "/data.json");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String inputText = searchBar.getText();
        textAreaContainer.getChildren().clear();
        searchButton.setOnMouseClicked(event -> {
            String searchParam = searchBar.getText();
            List<String> searchResultByKey = new ArrayList<>(rbTree.searchKeysAndValuesByContainingKey(searchParam));
            List<String> searchResultByValue = new ArrayList<>(rbTree.searchKeysAndValuesByContainingValue(searchParam));
            List<String> combinedResults = PrintsUtil.combineResults(new ArrayList<>(searchResultByKey), new ArrayList<>(searchResultByValue));

            listView.getItems().clear();
            listView.getItems().addAll(combinedResults);

            listView.setPrefHeight(Math.min(combinedResults.size() * 30, 535));
        });

        slider.setTranslateX(400);
        listView.setOnMouseClicked(event -> {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String key = selectedItem;
                String value = rbTree.getValueByKey(key);

                // String inputText = searchBar.getText().toLowerCase(); // Ambil input teks dari pengguna dan ubah menjadi lowercase
                // textAreaContainer.getChildren().clear();
        
                key = formatTextWithBold(key, inputText, "acak");
                value = formatTextWithBold(value, inputText, "acak");
                // String formattedKey = key.replace(inputText, "<b>" + inputText + "</b>");
                // String formattedValue = value.replace(inputText, "<b>" + inputText + "</b>");

                // if (key.toLowerCase().contains(inputText.toLowerCase()) || value.toLowerCase().contains(inputText.toLowerCase())) {
                    // WebView webView = new WebView();
                    // String content = "judul: " + formattedKey + "<br>isi konten: " + formattedValue;
                    // webView.getEngine().loadContent(content);
                    // textAreaContainer.getChildren().add(webView);
                // }
                
                WebView webView = new WebView();
                String content = "judul: " + key + "<br>isi konten: " + value;
                
                webView.getEngine().loadContent(content);
                textAreaContainer.getChildren().add(webView);
                // description.setText(content);

                searchBar.clear(); 
        // Tampilkan teks dalam WebView
                // String displayText =  key + "\n\n" + value;
                // description.setText(displayText);

                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.5));
                slide.setNode(slider);

                slide.setToX(0);
                slide.play();

                slider.setTranslateX(400);

                slide.setOnFinished((ActionEvent e) -> {
                    listView.setVisible(true);
                    backButton.setVisible(true);
                });
            }
        });


        backButton.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(slider);

            slide.setToX(400);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                listView.setVisible(true);
                backButton.setVisible(false);
            });
        });
    }
    private String formatTextWithBold(String text, String inputText, String keyword) {
        if (text != null && inputText != null) {
            String[] words = text.split("\\s+");
            StringBuilder formattedText = new StringBuilder();

            for (String word : words) {
                if (word.toLowerCase().contains(inputText.toLowerCase()) && word.toLowerCase().contains(keyword)) {
                    formattedText.append("<b>").append(word).append("</b>").append(" ");
                } else {
                    formattedText.append(word).append(" ");
                }
            }

            return formattedText.toString().trim();
        }

        return text;
    }
}



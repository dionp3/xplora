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
import javafx.concurrent.Worker;

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
                String contents = selectedItem;
                textAreaContainer.getChildren().clear();

                WebView webView = new WebView();
                String content = "judul: " + contents ;
                webView.getEngine().loadContent(content);
                applyTextHighlight(webView, searchBar.getText());
                textAreaContainer.getChildren().add(webView);

                searchBar.clear(); 


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

    private void applyTextHighlight(WebView webView, String inputText) {
        webView.getEngine().getLoadWorker().stateProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue == Worker.State.SUCCEEDED) {
                    String content = webView.getEngine().getDocument().getDocumentElement().getTextContent();

                    // Menyamakan warna bold dan gaya teks
                    content = content.replaceAll(inputText, String.format("<b>%s</b>", inputText));

                    // Mengatur teks dengan gaya khusus ke dalam WebView
                    webView.getEngine().loadContent(content);
                    webView.getEngine().setUserStyleSheetLocation(getClass().getResource("/css/styles.css").toString());
                }
            }
        );
    }
}



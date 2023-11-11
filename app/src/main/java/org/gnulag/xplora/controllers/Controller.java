package org.gnulag.xplora.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.gnulag.xplora.utils.PrintsUtil;
import org.gnulag.xplora.utils.RandomGimmick;
import org.gnulag.xplora.models.RedBlackTreeMap;
import org.gnulag.xplora.utils.GameGimmick;
import org.gnulag.xplora.utils.JSONUtil;

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
        rbTree.insert("random", null, new RandomGimmick<>());
        rbTree.insert("acak", null, new RandomGimmick<>());
        rbTree.insert("rock", null, new GameGimmick<>());
        rbTree.insert("paper", null, new GameGimmick<>());
        rbTree.insert("scissor", null, new GameGimmick<>());
        rbTree.insert("batu", null, new GameGimmick<>());
        rbTree.insert("gunting", null, new GameGimmick<>());
        rbTree.insert("kertas", null, new GameGimmick<>());
        JSONUtil.loadJsonData(rbTree, "/data.json");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchButton.setOnMouseClicked(event -> {
            String searchParam = searchBar.getText().toLowerCase();
            if (!searchParam.isEmpty()) {
                List<String> results = PrintsUtil.printRedBlackTreeResults(rbTree, searchParam);

                // Clear previous results
                listView.getItems().clear();

                // Display new results in the ListView
                for (String result : results) {
                    String[] resultSplit = result.split("\n");
                    String cutResult = "";
                    for (int i = 0; i < 5; i++) {
                            cutResult += resultSplit[i];
                    }
                    listView.getItems().add(cutResult);
                }
            }
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

                // searchBar.clear(); 


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



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
import javafx.fxml.Initializable;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.util.Duration;

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
                listView.getItems().addAll(results);
            }
        });

        slider.setTranslateX(400);
        listView.setOnMouseClicked(event -> {

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
}



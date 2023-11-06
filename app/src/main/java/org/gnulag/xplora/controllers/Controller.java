package org.gnulag.xplora.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.gnulag.xplora.utils.PrintsUtil;
import org.gnulag.xplora.models.RedBlackTreeMap;
import org.gnulag.xplora.utils.JsonUtil;
import org.gnulag.xplora.utils.PrintsUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable {

    @FXML
    private ListView<String> listView;

    @FXML
    private Button backButton;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private AnchorPane slider;

    private RedBlackTreeMap<String, String> rbTree;

    public Controller() {
        rbTree = new RedBlackTreeMap<>();
        JsonUtil.loadJsonData(rbTree, "/data.json");
    }

    @FXML
    void searchKeyOrValue(ActionEvent event) {
        String searchParam = searchBar.getText();
        List<String> searchResult = PrintsUtil.printCombinedOutput(rbTree, searchParam);
        listView.getItems().clear();
        listView.getItems().addAll(searchResult);
    }

    @FXML
    void closeSideBar(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

}

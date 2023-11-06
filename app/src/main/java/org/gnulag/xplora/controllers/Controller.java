package org.gnulag.xplora;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.gnulag.xplora.models.RedBlackTreeMap;
import org.gnulag.xplora.utils.JsonUtil;
import org.gnulag.xplora.utils.PrintsUtil;

public class Controller {
    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<String> outputListView;

    private RedBlackTreeMap<String, String> rbTree;

    public Controller() {
        rbTree = new RedBlackTreeMap<>();
        JsonUtil.loadJsonData(rbTree, "/data.json");
    }

    @FXML
    private void handleSearchButton() {
        String searchParam = searchTextField.getText();
        PrintsUtil.printCombinedOutput(rbTree, searchParam);
    }
}

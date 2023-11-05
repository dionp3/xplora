/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.gnulag.xplora;

import java.util.ArrayList;
import java.util.List;
import org.gnulag.xplora.models.RedBlackTreeMap;

import org.gnulag.xplora.utils.JsonUtil;
import org.gnulag.xplora.utils.PrintsUtil;

public class App {
    public static void main(String[] args) {
        RedBlackTreeMap<String, String> rbTree = new RedBlackTreeMap<>();
        List<String> combinedOutput = new ArrayList<>();

        JsonUtil.loadJsonData(rbTree, "/data.json");

        String searchParam = "acak";

        ArrayList<String> valueSearchKeyAndValuesByContainingKey = rbTree
                .searchKeysAndValuesByContainingKey(searchParam);
        ArrayList<String> valueSearchKeyAndValuesByContainingValue = rbTree
                .searchKeysAndValuesByContainingValue(searchParam);
        combinedOutput.addAll(valueSearchKeyAndValuesByContainingKey);
        combinedOutput.addAll(valueSearchKeyAndValuesByContainingValue);

        System.out.println(combinedOutput);
    }
}

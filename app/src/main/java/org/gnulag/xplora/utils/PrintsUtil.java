package org.gnulag.xplora.utils;

import javafx.scene.control.TextArea;
import org.gnulag.xplora.models.RedBlackTreeMap;
import java.util.List;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import java.util.HashSet;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class PrintsUtil {
    public static ArrayList<String> combineResults(ArrayList<String> resultByKey, ArrayList<String> resultByValue) {
        ArrayList<String> combinedResults = new ArrayList<>();

        // Iterasi melalui hasil dari pencarian berdasarkan key
        for (String item : resultByKey) {
            // Jika item belum ada dalam combinedResults, tambahkan
            if (!combinedResults.contains(item)) {
                combinedResults.add(item);
            }
        }

        // Iterasi melalui hasil dari pencarian berdasarkan value
        for (String item : resultByValue) {
            // Jika item belum ada dalam combinedResults, tambahkan
            if (!combinedResults.contains(item)) {
                combinedResults.add(item);
            }
        }

        // Panggil checkGimmick untuk menambahkan gimmick
        GimmickUtil.checkGimmick(combinedResults);

        return combinedResults;
    }
}

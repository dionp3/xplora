package org.gnulag.xplora.utils;

import java.util.ArrayList;

public class PrintsUtil {
    public static ArrayList<String> combineResults(ArrayList<String> resultByKey, ArrayList<String> resultByValue, String searchParam) {
        ArrayList<String> combinedResults = new ArrayList<>(resultByKey);

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

        String gimmick = GimmickUtil.checkGimmick(searchParam);
        if (!gimmick.isEmpty()) {
          combinedResults.add(0, gimmick);
        }
        return combinedResults;
    }
}


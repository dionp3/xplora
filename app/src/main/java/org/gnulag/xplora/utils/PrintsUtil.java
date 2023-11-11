package org.gnulag.xplora.utils;

import org.gnulag.xplora.models.RedBlackTreeMap;
import java.util.ArrayList;
import java.util.List;

public class PrintsUtil {
    public static List<String> printRedBlackTreeResults(RedBlackTreeMap<String, String> rbTree, String searchParam) {
        String result = rbTree.searchKeyAndValueByKey(searchParam);
        List<String> resultsByKey = rbTree.searchKeysAndValuesByContainingKey(searchParam);
        List<String> resultsByValue = rbTree.searchKeysAndValuesByContainingValue(searchParam);
        ArrayList<String> combinedResults = new ArrayList<>();

        // Now you can use the combinedResults list as needed
        for (String combinedResult : combinedResults) {
            System.out.println(combinedResult);
        }
        if (result != null) {
            combinedResults.add(result);
        }

        for (String item : resultsByKey) {
            if (!combinedResults.contains(item)) {
                combinedResults.add(item);
            }
        }
        for (String item : resultsByValue) {
            if (!combinedResults.contains(item)) {
                combinedResults.add(item);
            }
        }
        return combinedResults;
    }

}


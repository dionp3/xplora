package org.gnulag.xplora.utils;

import org.gnulag.xplora.models.RedBlackTreeMap;
import java.util.List;
import java.util.ArrayList;

/**
 * PrintsUtil
 */
public class PrintsUtil {
    public static void printCombinedOutput(RedBlackTreeMap<String, String> rbTree, String searchParam) {
        List<String> combinedOutput = new ArrayList<>();
        List<String> valueSearchKeyAndValuesByContainingKey = rbTree.searchKeysAndValuesByContainingKey(searchParam);
        List<String> valueSearchKeyAndValuesByContainingValue = rbTree
                .searchKeysAndValuesByContainingValue(searchParam);
        combinedOutput.addAll(valueSearchKeyAndValuesByContainingKey);
        combinedOutput.addAll(valueSearchKeyAndValuesByContainingValue);

        for (String output : combinedOutput) {
            System.out.println(output);
        }
    }
}

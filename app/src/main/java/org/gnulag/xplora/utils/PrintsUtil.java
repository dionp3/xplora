package org.gnulag.xplora.utils;

import org.gnulag.xplora.models.RedBlackTreeMap;
import java.util.List;

/**
 * PrintsUtil
 */
public class PrintsUtil {

    public static void printResults(RedBlackTreeMap<String, String> rbTree, List<String> keys) {
        for (String key : keys) {
            System.out.println(key);
            String value = rbTree.getValueByKey(key);
            if (value != null) {
                System.out.println(value);
            }
            System.out.println();
        }
    }

}

package org.gnulag.xplora.utils;

import org.gnulag.xplora.models.RedBlackTreeMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

/**
 * JsonUtil
 */
public class JsonUtil {
    public static void loadJsonData(RedBlackTreeMap<String, String> rbTree, String resourcePath) {
        try (InputStream jsonStream = JsonUtil.class.getResourceAsStream(resourcePath)) {
            if (jsonStream != null) {
                JSONArray jsonArray = new JSONArray(new JSONTokener(jsonStream));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String key = jsonObject.keys().next();
                    String value = jsonObject.getString(key);
                    rbTree.insert(key, value);
                }
            } else {
                System.err.println("Failed to load data.json. The resource is not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

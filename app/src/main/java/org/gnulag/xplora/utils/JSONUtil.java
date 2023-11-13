package org.gnulag.xplora.utils;

import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.gnulag.xplora.models.RedBlackTreeMap;

public class JSONUtil {
    public static void loadJsonData(RedBlackTreeMap<String, String> rbTree, String resourcePath) {
        try (InputStream jsonStream = JSONUtil.class.getResourceAsStream(resourcePath)) {
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


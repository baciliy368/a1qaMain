package framework.utils;

import org.json.JSONObject;

public class JsonParser {
    public static String getValue(JSONObject jsonObject, String key) {
        return jsonObject.getString(key);
    }
}

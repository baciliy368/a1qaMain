package framework.utils;

import aquality.selenium.utils.JsonFile;
import org.json.JSONObject;

public class JsonParser {
    public static Object getValue(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }

    public static String getDataFromUsersFile(String key) {
        return new JsonFile(PropertiesReader
                .getValue("PATH_TO_FILE_WITH_USER_DATA"))
                .getValue("/" + key)
                .toString();
    }
}

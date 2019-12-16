package framework.utils;

import aquality.selenium.logger.Logger;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser {
    public static String getValue(JSONObject jsonObject, String key) {

        return jsonObject.getString(key);
    }

    public static String getDataFromUsersFile(String key) {
        String result = "";
        try {
            result = new JSONObject(new String(Files.readAllBytes(
                    Paths.get(PropertiesReader.getValue("PATH_TO_FILE_WITH_USER_DATA"))))).getString(key);
        } catch (IOException e) {
            Logger.getInstance().error(e.getMessage());
        }
        return result;
    }
}

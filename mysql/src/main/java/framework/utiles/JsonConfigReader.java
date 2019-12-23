package framework.utiles;

import exceptions.NoConfigFileOrParameterException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class JsonConfigReader {
    private static final String PATH_TO_JSON_CONFIG = "src/main/resources/databaseconfig.json";

    public static String getJsonParameterValue(String parameter) {
        try (FileReader fileReader = new FileReader(PATH_TO_JSON_CONFIG)){
            return (String) ((JSONObject) new JSONParser().parse(fileReader)).get(parameter);
        } catch (Exception e) {
            throw new NoConfigFileOrParameterException(PATH_TO_JSON_CONFIG + e);
        }
    }
}

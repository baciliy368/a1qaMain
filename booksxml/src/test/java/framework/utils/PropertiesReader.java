package framework.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final String PROPERTIES_FILE_PATH = "src/test/resources/testData.properties";

    public static String getValue(String key) {
        Properties prop = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
            prop.load(fileInputStream);
        } catch (Exception e) {
            Log.error(e.getMessage());
        }
        return prop.getProperty(key);
    }
}


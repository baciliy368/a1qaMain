package framework.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class PropertiesReader {
    private static final String PATH_TO_PROPERTIES_FILE = "src/test/resources/testData.properties";

    public static String getValue(String key) {
        InputStream input;
        Properties prop = new Properties();
        try {
            input = new FileInputStream(PATH_TO_PROPERTIES_FILE);
            prop.load(input);
            return prop.getProperty(key);
        } catch (Exception e) {
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new NullPointerException();
        }
    }
}


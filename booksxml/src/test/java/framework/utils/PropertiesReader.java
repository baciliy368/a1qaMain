package framework.utils;

import exceptions.NoPropertiesFileException;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final String PROPERTIES_FILE_PATH = "src/test/resources/testData.properties";

    public static String getValue(String key) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/test/resources/testData.properties"));
            return prop.getProperty(key);
        } catch (Exception e) {
            Log.LOG.error(e.getMessage());
            throw new NoPropertiesFileException(PROPERTIES_FILE_PATH + e);
        }
    }
}


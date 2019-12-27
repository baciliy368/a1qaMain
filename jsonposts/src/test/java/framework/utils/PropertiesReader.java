package framework.utils;

import exceptions.NoPropertiesFileException;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;

public class PropertiesReader {
    private static final String PROPERTIES_FILE_PATH = "src/test/resources/testData/testData.properties";

    public static String getValue(String key) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(PROPERTIES_FILE_PATH));
            return prop.getProperty(key);
        } catch (Exception e) {
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new NoPropertiesFileException(PROPERTIES_FILE_PATH + e);
        }
    }
}


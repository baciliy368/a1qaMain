package framework.utiles;

import exceptions.NoConfigFileOrParameterException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final String PATH_TO_PROPERTIES_FILE = "src/main/resources/sqlscripts.properties";

    public static String getValue(String key) {
        InputStream input;
        Properties prop = new Properties();
        try {
            input = new FileInputStream(PATH_TO_PROPERTIES_FILE);
            prop.load(input);
        } catch (Exception e) {
            throw new NoConfigFileOrParameterException(PATH_TO_PROPERTIES_FILE + e);
        }
        return prop.getProperty(key);
    }
}


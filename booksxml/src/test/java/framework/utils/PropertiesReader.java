package framework.utils;

import exceptions.NoPropertiesFileException;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {

    public static String getValue(String key) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/test/resources/testData.properties"));
        } catch (Exception e) {
            LoggerOfTests.logger.error(e.getMessage());
            throw new NoPropertiesFileException();
        }
        return prop.getProperty(key);
    }
}


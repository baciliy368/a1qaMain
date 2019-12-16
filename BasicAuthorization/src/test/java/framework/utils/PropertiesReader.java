package framework.utils;

import aquality.selenium.logger.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static Logger logger = Logger.getInstance();

    public static String getValue(String key) {
        InputStream input = null;
        try {
            input = new FileInputStream("src/test/resources/testData.properties");
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
        Properties prop = new Properties();
        try {
            prop.load(input);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return prop.getProperty(key);
    }
}

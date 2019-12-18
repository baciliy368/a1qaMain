package framework.utils;

import aquality.selenium.logger.Logger;
import exceptions.NoPropertiesFileException;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static Logger logger = Logger.getInstance();

    public static String getValue(String key) {
        InputStream input;
        Properties prop = new Properties();
        try {
            input = new FileInputStream("src/test/resources/testData.properties");
            prop.load(input);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NoPropertiesFileException();
        }
        return prop.getProperty(key);
    }
}


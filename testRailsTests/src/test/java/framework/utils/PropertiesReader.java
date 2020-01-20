package framework.utils;

import framework.exceptions.ExceptionWrapper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private String pathToPropertiesFile = "src/test/resources/testData.properties";

    public PropertiesReader() {
    }

    public PropertiesReader(String customFilePath) {
        pathToPropertiesFile = customFilePath;
    }

    public String getValue(String key) {
        InputStream input;
        Properties prop = new Properties();
        try {
            input = new FileInputStream(pathToPropertiesFile);
            prop.load(input);
            return prop.getProperty(key);
        } catch (Exception e) {
            Log.error(String.format("Can`t read properties file %s", pathToPropertiesFile), e);
            throw new ExceptionWrapper(String.format("Problems with reading properties file %s", pathToPropertiesFile), e);
        }
    }
}


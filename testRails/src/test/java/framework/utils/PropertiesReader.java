package framework.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
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
            final NoSuchElementException noSuchElementException
                    = new NoSuchElementException(String.format("Problems with reading properties file %s", pathToPropertiesFile));
            Log.error(noSuchElementException.getMessage(), Arrays.toString(e.getStackTrace()));
            throw noSuchElementException;
        }
    }
}


package framework.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerOfTests {
    public static final Logger logger = Logger.getLogger(LoggerOfTests.class);

    public static void setUpLogger() {
        PropertyConfigurator.configure(PropertiesReader.getValue("logFilePropertyPath"));
    }
}

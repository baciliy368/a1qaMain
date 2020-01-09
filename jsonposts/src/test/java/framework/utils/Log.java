package framework.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
    private static final Logger LOG = Logger.getLogger(Log.class);

    public static void setUpLogger() {
        PropertyConfigurator.configure(PropertiesReader.getValue("logFilePropertyPath"));
    }

    public static void info(String message) {
        LOG.info(message);
    }

    public static void error(String message) {
        LOG.error(message);
    }

    public static void step(int numberOfStep, String message) {
        LOG.info(String.format("STEP %d: %s", numberOfStep, message));
    }
}

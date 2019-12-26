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

    public static <T> void error(T message) {
        LOG.error(message);
    }
}

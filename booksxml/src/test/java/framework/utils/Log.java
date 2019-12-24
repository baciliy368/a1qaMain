package framework.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
    public static final Logger LOG = Logger.getLogger(Log.class);

    public static void setUpLogger() {
        PropertyConfigurator.configure(PropertiesReader.getValue("logFilePropertyPath"));
    }
}

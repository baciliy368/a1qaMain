package framework.utils;

import aquality.selenium.logger.Logger;

public class Log {

    public static void error(String message, Throwable error) {
        Logger.getInstance().error(String.format("%s\n%s", message, error));
    }

    public static void step(int numberOfStep, String message) {
        Logger.getInstance().info(String.format("STEP %d: %s", numberOfStep, message));
    }

    public static void info(String message) {
        Logger.getInstance().info(message);
    }
}

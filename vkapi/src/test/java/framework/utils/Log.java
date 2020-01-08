package framework.utils;

import aquality.selenium.logger.Logger;

public class Log {

    public static void error(String message) {
        Logger.getInstance().error(message);
    }

    public static void step(int numberOfStep, String message) {
        Logger.getInstance().info(String.format("STEP %d: %s", numberOfStep, message));
    }
}

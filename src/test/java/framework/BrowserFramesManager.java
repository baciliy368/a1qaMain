package framework;

import aquality.selenium.browser.BrowserManager;
import aquality.selenium.logger.Logger;

public class BrowserFramesManager {
    private static Logger logger = Logger.getInstance();

    public static void switchDriverOnFrameByIndex(int indexOfFrame) {
        logger.info(String.format("switch driver on %d frame", indexOfFrame));
        BrowserManager.getBrowser().getDriver().switchTo().frame(indexOfFrame);
    }

    public static void  switchDriverOnDefaultContent() {
        logger.info("switch driver on default frame");
        BrowserManager.getBrowser().getDriver().switchTo().defaultContent();
    }


}


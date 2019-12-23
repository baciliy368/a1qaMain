package framework;

import aquality.selenium.browser.BrowserManager;
import aquality.selenium.logger.Logger;
import pageobject.IFrame;

public class BrowserFramesManager {
    private static Logger logger = Logger.getInstance();

    public static void switchDriverOnFrameByIndex(IFrame frame) {
        logger.info(String.format("switch driver on %s frame", frame));
        BrowserManager.getBrowser().getDriver().switchTo().frame(frame.getId());
    }

    public static void  switchDriverOnDefaultContent() {
        logger.info("switch driver on default frame");
        BrowserManager.getBrowser().getDriver().switchTo().defaultContent();
    }
}


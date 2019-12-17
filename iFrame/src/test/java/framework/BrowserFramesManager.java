package framework;

import aquality.selenium.browser.BrowserManager;
import aquality.selenium.logger.Logger;
import pageobject.FramesOnMainExamplePage;

public class BrowserFramesManager {
    private static Logger logger = Logger.getInstance();

    public static void switchDriverOnFrameByIndex(FramesOnMainExamplePage frame) {
        logger.info(String.format("switch driver on %s frame", frame));
        BrowserManager.getBrowser().getDriver().switchTo().frame(frame.getIdStream());
    }

    public static void  switchDriverOnDefaultContent() {
        logger.info("switch driver on default frame");
        BrowserManager.getBrowser().getDriver().switchTo().defaultContent();
    }
}


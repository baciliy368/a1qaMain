package framework;

import aquality.selenium.browser.BrowserManager;
import aquality.selenium.logger.Logger;
import framework.utils.Log;
import pageobject.IFrame;

public class BrowserFramesManager {

    public static void switchDriverOnFrameByIndex(IFrame frame) {
        Log.info(String.format("switch driver on %s frame", frame));
        BrowserManager.getBrowser().getDriver().switchTo().frame(frame.getId());
    }

    public static void  switchDriverOnDefaultContent() {
        Log.info("switch driver on default frame");
        BrowserManager.getBrowser().getDriver().switchTo().defaultContent();
    }
}


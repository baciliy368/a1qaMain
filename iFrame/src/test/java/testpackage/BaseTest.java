package testpackage;

import aquality.selenium.browser.BrowserManager;
import aquality.selenium.logger.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected Logger logger = Logger.getInstance();

    @BeforeTest
    public void beforeTest() {
        BrowserManager.getBrowser().maximize();
    }

    @AfterTest
    public void afterTest() {
        BrowserManager.getBrowser().quit();
    }
}

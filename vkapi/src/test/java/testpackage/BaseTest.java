package testpackage;

import aquality.selenium.browser.BrowserManager;
import framework.utils.PropertiesReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected static final String MAIN_PAGE = PropertiesReader.getValue("VK_COM");

    @BeforeTest
    public void beforeTest() {
        BrowserManager.getBrowser().maximize();
    }

    @AfterTest
    public void afterTest() {
        BrowserManager.getBrowser().quit();
    }
}

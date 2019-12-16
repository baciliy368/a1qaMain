import aquality.selenium.browser.BrowserManager;
import aquality.selenium.logger.Logger;
import framework.BrowserFramesManager;
import framework.utils.PropertiesReader;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.MainPage;

public class AlertWindowsTest {
    private Logger logger = Logger.getInstance();
    private int numberOfRandomSymbolsInString = 10;
    private String randomString = RandomStringUtils.randomAlphabetic(numberOfRandomSymbolsInString);

    @Test
    public void jsAlertTest() {
        logger.info("Step 1: Open Main page");
        BrowserManager.getBrowser().goTo(PropertiesReader.getValue("START_PAGE_FRAME_TEST"));
        MainPage mainPage = new MainPage();
        logger.info("Check mainPage isOpened");
        Assert.assertTrue(mainPage.isFormDisplayed(), "Form mainPage is not displayed");

        logger.info("Step 2:");
        BrowserFramesManager.switchDriverOnFrameByIndex(0);
        mainPage.getExampleForm().getFrameForm().clearAndType(randomString);
        logger.info("Check default text");
        Assert.assertEquals(mainPage.getExampleForm().getFrameForm().getTextDefault(),
                randomString, "texts are not match");

        logger.info("Step 3:");
        mainPage.getExampleForm().getFrameForm().selectAllText();
        BrowserFramesManager.switchDriverOnDefaultContent();
        mainPage.getExampleForm().clickBtnBold();
        BrowserFramesManager.switchDriverOnFrameByIndex(0);
        logger.info("Check strong text");
        Assert.assertEquals(mainPage.getExampleForm().getFrameForm().getTextStrong(),
                randomString, "texts are not match");
    }
}

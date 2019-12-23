package testpackage;

import aquality.selenium.browser.BrowserManager;
import framework.BrowserFramesManager;
import framework.utils.PropertiesReader;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.MainPage;
import pageobject.TextInputFrame;

public class TestInputFrameArea extends BaseTest {
    private int numberOfRandomSymbolsInString = 10;
    private String randomString = RandomStringUtils.randomAlphabetic(numberOfRandomSymbolsInString);

    @Test
    public void testInputTextInFrame() {
        logger.info("Step 1: Open Main page");
        BrowserManager.getBrowser().goTo(PropertiesReader.getValue("START_PAGE_FRAME_TEST"));
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isFormDisplayed(), "Form mainPage is not displayed");

        logger.info("Step 2: clear and type in input field");
        TextInputFrame textInputFrame = mainPage.getExampleForm().getTextInputFrame();
        BrowserFramesManager.switchDriverOnFrameByIndex(textInputFrame);
        mainPage.getExampleForm().getTextInputFrame().clearAndType(randomString);
        Assert.assertEquals(randomString, textInputFrame.getTextDefault(),
                "texts are not match");

        logger.info("Step 3: make text strong");
        mainPage.getExampleForm().getTextInputFrame().selectAllText();
        BrowserFramesManager.switchDriverOnDefaultContent();
        mainPage.getExampleForm().clickBtnBold();
        BrowserFramesManager.switchDriverOnFrameByIndex(textInputFrame);
        Assert.assertEquals(randomString, textInputFrame.getTextStrong(),
                "texts are not match");
    }
}

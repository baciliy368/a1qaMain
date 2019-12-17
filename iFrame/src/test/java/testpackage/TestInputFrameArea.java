package testpackage;

import aquality.selenium.browser.BrowserManager;
import framework.BrowserFramesManager;
import framework.utils.PropertiesReader;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.FramesOnMainExamplePage;
import pageobject.MainPage;

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
        BrowserFramesManager.switchDriverOnFrameByIndex(FramesOnMainExamplePage.textInputArea);
        mainPage.getExampleForm().getFrameForm().clearAndType(randomString);
        Assert.assertEquals(randomString, mainPage.getExampleForm().getFrameForm().getTextDefault(),
                "texts are not match");

        logger.info("Step 3: make text strong");
        mainPage.getExampleForm().getFrameForm().selectAllText();
        BrowserFramesManager.switchDriverOnDefaultContent();
        mainPage.getExampleForm().clickBtnBold();
        BrowserFramesManager.switchDriverOnFrameByIndex(FramesOnMainExamplePage.textInputArea);
        Assert.assertEquals(randomString, mainPage.getExampleForm().getFrameForm().getTextStrong(),
                "texts are not match");
    }
}

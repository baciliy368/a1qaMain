package testpackage;

import aquality.selenium.browser.BrowserManager;
import framework.BrowserFramesManager;
import framework.utils.PropertiesReader;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
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
        BrowserFramesManager.switchDriverOnFrameByIndex(mainPage.getExampleForm().getInputTextFieldFrame().getInputFrame());
        mainPage.getExampleForm().getInputTextFieldFrame().clearAndType(randomString);
        Assert.assertEquals(randomString, mainPage.getExampleForm().getInputTextFieldFrame().getTextDefault(),
                "texts are not match");

        logger.info("Step 3: make text strong");
        mainPage.getExampleForm().getInputTextFieldFrame().selectAllText();
        BrowserFramesManager.switchDriverOnDefaultContent();
        mainPage.getExampleForm().clickBtnBold();
        BrowserFramesManager.switchDriverOnFrameByIndex(mainPage.getExampleForm().getInputTextFieldFrame().getInputFrame());
        Assert.assertEquals(randomString, mainPage.getExampleForm().getInputTextFieldFrame().getTextStrong(),
                "texts are not match");
    }
}

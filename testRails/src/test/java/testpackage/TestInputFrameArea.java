package testpackage;

import aquality.selenium.browser.BrowserManager;
import framework.BrowserFramesManager;
import framework.utils.Log;
import framework.utils.PropertiesReader;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.MainPage;
import pageobject.TextInputFrame;

public class TestInputFrameArea extends BaseTest {
    private static final int NUMBER_OF_RANDOM_SYMBOLS_IN_STRING = 10;
    private static final String RANDOM_STRING = RandomStringUtils.randomAlphabetic(NUMBER_OF_RANDOM_SYMBOLS_IN_STRING);

    private TestInputFrameArea() {
        caseId = "15878661";
    }

    @Test
    public void testInputTextInFrame() {
        Log.step(1,"Open Main page");
        BrowserManager.getBrowser().goTo(new PropertiesReader().getValue("startPageFrameTest"));
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isFormDisplayed(), "Form mainPage is not displayed");

        Log.step(2, "clear and type in input field");
        TextInputFrame textInputFrame = mainPage.getExampleForm().getTextInputFrame();
        BrowserFramesManager.switchDriverOnFrameByIndex(textInputFrame);
        mainPage.getExampleForm().getTextInputFrame().clearAndType(RANDOM_STRING);
        Assert.assertEquals(RANDOM_STRING, textInputFrame.getTextDefault(),
                "texts are not match");

        Log.step(3, "make text strong");
        mainPage.getExampleForm().getTextInputFrame().selectAllText();
        BrowserFramesManager.switchDriverOnDefaultContent();
        mainPage.getExampleForm().clickBtnBold();
        BrowserFramesManager.switchDriverOnFrameByIndex(textInputFrame);
        Assert.assertEquals(RANDOM_STRING, textInputFrame.getTextStrong(),
                "texts are not match");
    }
}

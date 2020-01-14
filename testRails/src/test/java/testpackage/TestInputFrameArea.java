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
    public static final String RUN_ID = "34329";
    public static final String CASE_ID = "15878661";

    @Test
    public void testInputTextInFrame() {
        Log.step(1,"Open Main page");
        BrowserManager.getBrowser().goTo(new PropertiesReader().getValue("START_PAGE_FRAME_TEST"));
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

    @Override
    public String getRunId() {
        return RUN_ID;
    }

    @Override
    public String getCaseId() {
        return CASE_ID;
    }
}

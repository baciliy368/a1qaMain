package testpackage;

import aquality.selenium.browser.BrowserManager;
import framework.utils.ModelGenerator;
import framework.utils.PropertiesReader;
import framework.utils.ScreenManager;
import models.UserModel;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import testrail.api.TestRailsActions;
import testrail.enums.ParamsPostTestRails;
import testrail.enums.TestCondition;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    private static final File USER_CONFIG_FILE = new File(PropertiesReader.getValue("USER"));

    @BeforeTest
    public void beforeTest() {
        BrowserManager.getBrowser().maximize();
    }

    @AfterTest
    public void afterTest() {
        BrowserManager.getBrowser().quit();
    }

    public static void registerOnTestRails(TestCondition condition, String runId, String caseId) {
        ScreenManager.takeScreenShot();
        UserModel user = ModelGenerator.getModelByMapping(USER_CONFIG_FILE, UserModel.class);
        TestRailsActions testRailsActions = new TestRailsActions();
        testRailsActions.configurator(user);
        Map<ParamsPostTestRails, Object> data = new HashMap<>();
        data.put(ParamsPostTestRails.STATUS_OF_TEST, condition);
        data.put(ParamsPostTestRails.COMMENT, "testing was conducted");
        String idOfResult = testRailsActions.addResultForCase(runId, caseId, data);
        testRailsActions.addAttachToResult(idOfResult, PropertiesReader.getValue("PATH_TO_SAVE_SCREENSHOT"));
    }
}

package testpackage;

import aquality.selenium.browser.BrowserManager;
import framework.utils.PropertiesReader;
import models.AddTestResultResponseModel;
import models.TestResultModel;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import testrail.api.TestRailApi;
import testrail.api.TestRailHelper;

public class BaseTest {
    private static final String RUN_ID = "34329";
    protected static String caseId = null;
    private static byte[] screenShot;
    private static final String PATH_TO_TEST_RAIL_CONFIG = "src/test/resources/test/testRail.properties";
    private static final PropertiesReader testRailProperties = new PropertiesReader(PATH_TO_TEST_RAIL_CONFIG);
    private static final String USER = testRailProperties.getValue("user");
    private static final String PASSWORD = testRailProperties.getValue("password");
    private static final String COMMENT = "test was conducted";

    @BeforeTest
    public void beforeTest() {
        BrowserManager.getBrowser().maximize();
    }

    @AfterTest
    public void afterTest() {
        BrowserManager.getBrowser().quit();
    }

    @AfterMethod
    public void makeScreenshot() {
        screenShot = BrowserManager.getBrowser().getScreenshot();
    }

    @AfterMethod
    public void sendResultToTestRail(ITestResult result) {
        registerOnTestRails(result.getStatus());
    }

    private void registerOnTestRails(int result) {
        TestRailApi testRailApi = new TestRailApi(USER, PASSWORD);
        TestResultModel testResult = new TestRailHelper().getTestResult(result, COMMENT);
        AddTestResultResponseModel addTestResultResponseModel = testRailApi.getTestResult(RUN_ID, caseId, testResult);
        testRailApi.uploadFileByUrl(addTestResultResponseModel, screenShot);
    }
}

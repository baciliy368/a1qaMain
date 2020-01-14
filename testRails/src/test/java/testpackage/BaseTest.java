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
import testrail.api.TestRailHealer;

public class BaseTest {
    public static String RUN_ID;
    public static String CASE_ID;
    public static final PropertiesReader defaultPropertiesReader = new PropertiesReader();
    private static byte[] screenShot;
    private static final String PATH_TO_TEST_RAIL_CONFIG = "src/test/resources/test/testRail.properties";
    private static final PropertiesReader customPropertiesReader = new PropertiesReader(PATH_TO_TEST_RAIL_CONFIG);
    private static final String USER = customPropertiesReader.getValue("USER");
    private static final String PASSWORD = customPropertiesReader.getValue("PASSWORD");
    private static final String COMMENT = customPropertiesReader.getValue("COMMENT_AFTER_TEST");

    @BeforeTest
    public void beforeTest() {
        BrowserManager.getBrowser().maximize();
    }

    @AfterTest
    public void afterTest() {
        BrowserManager.getBrowser().quit();
    }


    @AfterMethod
    public  void makeScreenshot() {
        screenShot = BrowserManager.getBrowser().getScreenshot();
    }

    @AfterMethod
    public void sendResultToTestRail(ITestResult result) {
        registerOnTestRails(result.getStatus());
    }

    public String getRunId() {
        return RUN_ID;
    }

    public String getCaseId() {
        return CASE_ID;
    }

    private void registerOnTestRails(int result) {
        TestRailApi testRailApi = new TestRailApi(USER, PASSWORD);
        TestResultModel testResult = new TestRailHealer().getTestResult(result, COMMENT);
        AddTestResultResponseModel addTestResultResponseModel = testRailApi.getTestResult(getRunId(), getCaseId(), testResult);
        testRailApi.uploadFileByUrl(addTestResultResponseModel, screenShot);
    }
}

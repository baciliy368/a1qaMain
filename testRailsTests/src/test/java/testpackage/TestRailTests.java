package testpackage;

import framework.utils.Log;
import framework.utils.ModelsComparator;
import framework.utils.PropertiesReader;
import models.testrail.request.CaseRequestModel;
import models.testrail.request.RunRequestModel;
import models.testrail.request.SectionRequestModel;
import models.testrail.request.StepRequestModel;
import models.testrail.request.SuiteRequestModel;
import models.testrail.request.TestResultRequestModel;
import models.testrail.response.CaseResponseModel;
import models.testrail.response.RunResponseModel;
import models.testrail.response.SectionResponseModel;
import models.testrail.response.SuiteResponseModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import testrail.api.TestRailApi;
import testrail.api.TestRailHelper;
import java.util.List;

public class TestRailTests {
    private static final String PATH_TO_TEST_RAIL_CONFIG = "src/test/resources/test/testRail.properties";
    private static final PropertiesReader testRailProperties = new PropertiesReader(PATH_TO_TEST_RAIL_CONFIG);
    private static final String USER = testRailProperties.getValue("user");
    private static final String PASSWORD = testRailProperties.getValue("password");
    private static final String COMMENT = "test was conducted";
    private static final String PATH_TO_SUITE = testRailProperties.getValue("testCasePath");
    private static final String SUITE_NAME = TestRailHelper.getRandomSuiteName();
    private static final String SECTION_NAME = TestRailHelper.getRandomSectionName();
    private static final String CASE_NAME = TestRailHelper.getRandomCaseName();
    private static final String RUN_NAME = TestRailHelper.getRandomRunName();
    private static final String PROJECT_ID = testRailProperties.getValue("numberOfProject");

    @Test
    public static void testRailLifeCycleOfTest() {
        TestRailApi testRailApi = new TestRailApi(USER, PASSWORD);

        Log.step(1, "Create suite");
        SuiteRequestModel suiteRequestModel = TestRailHelper.getSuiteRequestModel(SUITE_NAME, "my test");
        SuiteResponseModel suiteResponseModel = testRailApi.addSuite(suiteRequestModel, PROJECT_ID);

        Log.step(2, "Check suite");
        Assert.assertTrue(ModelsComparator.areEquals(testRailApi.getSuite(suiteResponseModel.getId()), suiteRequestModel),
                "Suite is not created");

        Log.step(3, "Create section");
        SectionRequestModel sectionRequestModel = TestRailHelper.getSectionModel(suiteResponseModel.getId(), SECTION_NAME);
        SectionResponseModel sectionResponseModel = testRailApi.addSection(sectionRequestModel, PROJECT_ID);

        Log.step(4, "Check section");
        Assert.assertTrue(ModelsComparator.areEquals(testRailApi.getSection(sectionResponseModel.getId()), sectionRequestModel),
                "Section is not created");

        Log.step(5, "Create case");
        List<StepRequestModel> stepsModelFromJsonFile = TestRailHelper.getStepsModelFromJsonFile(PATH_TO_SUITE);
        CaseRequestModel caseRequestModel = TestRailHelper.getCaseModel(CASE_NAME, stepsModelFromJsonFile);
        CaseResponseModel caseResponseModel = testRailApi.addSteps(caseRequestModel, sectionResponseModel.getId());

        Log.step(6, "Check is case");
        Assert.assertTrue(ModelsComparator.areEquals(testRailApi.getCase(caseResponseModel.getId()), caseRequestModel),
                "CaseModel is not created");

        Log.step(7, "Create run");
        RunRequestModel runRequestModel = TestRailHelper.getRunRequestModel(RUN_NAME, suiteResponseModel.getId());
        RunResponseModel runResponseModel = testRailApi.addRun(runRequestModel, PROJECT_ID);

        Log.step(8, "Check run");
        Assert.assertTrue(ModelsComparator.areEquals(testRailApi.getRun(runResponseModel.getId()), runRequestModel),
                "CaseModel is not created");

        Log.step(9, "Change result of test");
        int resultOfTest = TestRailHelper.getRandomResult();
        TestResultRequestModel testResultRequestModel = TestRailHelper.getTestResult(resultOfTest, COMMENT);
        testRailApi.addTestResult(testResultRequestModel, runResponseModel.getId(), caseResponseModel.getId());

        Log.step(10, "Delete run");
        testRailApi.deleteRun(runResponseModel.getId());

        Log.step(11, "Delete case");
        testRailApi.deleteCase(caseResponseModel.getId());

        Log.step(12, "Delete section");
        testRailApi.deleteSection(sectionResponseModel.getId());

        Log.step(13, "Delete suite");
        testRailApi.deleteSuite(suiteResponseModel.getId());
    }
}

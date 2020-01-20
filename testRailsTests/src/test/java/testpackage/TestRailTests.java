package testpackage;

import framework.utils.Log;
import framework.utils.ModelsComparator;
import framework.utils.PropertiesReader;
import models.testrail.SectionModel;
import models.testrail.TestResultModel;
import models.testrail.CaseModel;
import models.testrail.RunModel;
import models.testrail.SuiteModel;
import models.testrail.StepModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import testrail.api.TestRailApi;
import testrail.api.TestRailHelper;

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

        Log.step(1, "creating suite");
        SuiteModel suite = TestRailHelper.getSuiteRequestModel(SUITE_NAME, "my test");
        SuiteModel suiteModel = testRailApi.addSuite(suite, PROJECT_ID);
        System.out.println(suiteModel.getUrl());

        Log.step(2, "check suite creation");
        Assert.assertTrue(ModelsComparator.isEquals(testRailApi.getSuite(suiteModel.getId()), suite),
                "Suite is not created");

        Log.step(3, "creation section");
        SectionModel sectionToRequest = TestRailHelper.getSectionModel(suiteModel.getId(), SECTION_NAME);
        SectionModel section = testRailApi.addSection(sectionToRequest, PROJECT_ID);

        Log.step(4, "check section creation");
        Assert.assertTrue(ModelsComparator.isEquals(testRailApi.getSection(section.getId()), sectionToRequest),
                "Section is not created");

        Log.step(5, "creation case");
        StepModel[] stepsModelFromJsonFile = TestRailHelper.getStepsModelFromJsonFile(PATH_TO_SUITE);
        CaseModel caseRequestModel = TestRailHelper.getCaseModel(CASE_NAME, stepsModelFromJsonFile);
        CaseModel caseModel = testRailApi.addSteps(caseRequestModel, section.getId());

        Log.step(6, "check is case created");
        Assert.assertTrue(ModelsComparator.isEquals(testRailApi.getCase(caseModel.getId()), caseRequestModel),
                "CaseModel is not created");

        Log.step(7, "create run");
        RunModel runRequestModel = TestRailHelper.getRunRequestModel(RUN_NAME, suiteModel.getId());
        RunModel run = testRailApi.addRun(runRequestModel, PROJECT_ID);

        Log.step(8, "check is run created");
        Assert.assertTrue(ModelsComparator.isEquals(testRailApi.getRun(run.getId()), runRequestModel),
                "CaseModel is not created");

        Log.step(9, "change result of test");
        int resultOfTest = TestRailHelper.getRandomResult();
        TestResultModel testResultRequestModel = TestRailHelper.getTestResult(resultOfTest, COMMENT);
        testRailApi.addTestResult(testResultRequestModel, run.getId(), caseModel.getId());

        Log.step(10, "run deletion");
        testRailApi.deleteRun(run.getId());

        Log.step(11, "case deletion");
        testRailApi.deleteCase(caseModel.getId());

        Log.step(12, "section deletion");
        testRailApi.deleteSection(section.getId());

        Log.step(13, "suite deletion");
        testRailApi.deleteSuite(suiteModel.getId());
    }
}

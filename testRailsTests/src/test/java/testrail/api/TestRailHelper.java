package testrail.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import framework.exceptions.ExceptionWrapper;
import framework.utils.Log;
import framework.utils.RandomStringGenerator;
import models.testrail.CaseModel;
import models.testrail.RunModel;
import models.testrail.SectionModel;
import models.testrail.StepModel;
import models.testrail.SuiteModel;
import models.testrail.TestResultModel;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class TestRailHelper {
    private static final int NUMBER_OF_RANDOM_STRING_IN_NAME = 5;

    public static TestResultModel getTestResult(int result, String comment) {
        TestResultModel testResult = new TestResultModel();
        testResult.setStatusId(String.valueOf(result));
        testResult.setComment(comment);
        return testResult;
    }


    public static SuiteModel getSuiteRequestModel(String name, String description) {
        SuiteModel suiteRequestModel = new SuiteModel();
        suiteRequestModel.setName(name);
        suiteRequestModel.setDescription(description);
        return suiteRequestModel;
    }

    public static SectionModel getSectionModel(String suiteId, String name) {
        SectionModel sectionModel = new SectionModel();
        sectionModel.setSuiteId(suiteId);
        sectionModel.setName(name);
        return sectionModel;
    }

    public static CaseModel getCaseModel(String title, StepModel[] stepModels) {
        CaseModel caseModel = new CaseModel();
        caseModel.setTitle(title);
        caseModel.setSteps(stepModels);
        return caseModel;
    }

    public static int getRandomResult() {
        return new Random().nextInt(1) + 1;
    }

    public static StepModel[] getStepsModelFromJsonFile(String path) {
        try {
            return new ObjectMapper().readValue(new File(path), StepModel[].class);
        } catch (IOException e) {
            Log.error("Problems with creating model", e);
            throw new ExceptionWrapper("Problems with creating model", e);
        }
    }

    public static RunModel getRunRequestModel(String name, String suiteId) {
        RunModel runRequestModel = new RunModel();
        runRequestModel.setName(name);
        runRequestModel.setSuiteId(suiteId);
        return runRequestModel;
    }

    public static String getRandomSuiteName() {
        return RandomStringGenerator.getRandomString("suite_", NUMBER_OF_RANDOM_STRING_IN_NAME);
    }

    public static String getRandomCaseName() {
        return RandomStringGenerator.getRandomString("case_", NUMBER_OF_RANDOM_STRING_IN_NAME);
    }

    public static String getRandomRunName() {
        return RandomStringGenerator.getRandomString("run_", NUMBER_OF_RANDOM_STRING_IN_NAME);
    }

    public static String getRandomSectionName() {
        return RandomStringGenerator.getRandomString("section_", NUMBER_OF_RANDOM_STRING_IN_NAME);
    }
}

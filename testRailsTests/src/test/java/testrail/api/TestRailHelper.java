package testrail.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import framework.exceptions.ExceptionWrapper;
import framework.utils.Log;
import framework.utils.RandomStringGenerator;
import models.testrail.request.CaseRequestModel;
import models.testrail.request.RunRequestModel;
import models.testrail.request.SectionRequestModel;
import models.testrail.request.StepRequestModel;
import models.testrail.request.SuiteRequestModel;
import models.testrail.request.TestResultRequestModel;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestRailHelper {
    private static final int NUMBER_OF_RANDOM_STRING_IN_NAME = 5;

    public static TestResultRequestModel getTestResult(int result, String comment) {
        TestResultRequestModel testResult = new TestResultRequestModel();
        testResult.setStatusId(String.valueOf(result));
        testResult.setComment(comment);
        return testResult;
    }

    public static SuiteRequestModel getSuiteRequestModel(String name, String description) {
        SuiteRequestModel suiteRequestModel = new SuiteRequestModel();
        suiteRequestModel.setName(name);
        suiteRequestModel.setDescription(description);
        return suiteRequestModel;
    }

    public static SectionRequestModel getSectionModel(String suiteId, String name) {
        SectionRequestModel sectionModel = new SectionRequestModel();
        sectionModel.setSuiteId(suiteId);
        sectionModel.setName(name);
        return sectionModel;
    }

    public static CaseRequestModel getCaseModel(String title, List<StepRequestModel> stepModels) {
        CaseRequestModel caseResponseModel = new CaseRequestModel();
        caseResponseModel.setTitle(title);
        caseResponseModel.setSteps(stepModels);
        return caseResponseModel;
    }

    public static int getRandomResult() {
        return new Random().nextInt(1) + 1;
    }

    public static List<StepRequestModel> getStepsModelFromJsonFile(String path) {
        try {
            return Arrays.asList(new ObjectMapper().readValue(new File(path), StepRequestModel[].class));
        } catch (IOException e) {
            Log.error("Problems with creating model", e);
            throw new ExceptionWrapper("Problems with creating model", e);
        }
    }

    public static RunRequestModel getRunRequestModel(String name, String suiteId) {
        RunRequestModel runRequestModel = new RunRequestModel();
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

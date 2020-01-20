package testrail.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import framework.exceptions.ExceptionWrapper;
import framework.utils.Log;
import framework.utils.PropertiesReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.testrail.request.CaseRequestModel;
import models.testrail.request.RunRequestModel;
import models.testrail.request.SectionRequestModel;
import models.testrail.request.SuiteRequestModel;
import models.testrail.request.TestResultRequestModel;
import models.testrail.response.CaseResponseModel;
import models.testrail.response.RunResponseModel;
import models.testrail.response.SectionResponseModel;
import models.testrail.response.SuiteResponseModel;
import models.testrail.response.TestResultResponseModel;
import testrail.enums.TestRailEndpoint;

import java.util.Base64;

public class TestRailApi {
    private static final String PATH_TO_TEST_RAIL_CONFIG = "src/test/resources/test/testRail.properties";
    private static final PropertiesReader customPropertiesReader = new PropertiesReader(PATH_TO_TEST_RAIL_CONFIG);
    private static final String BASIC_URL = customPropertiesReader.getValue("testRailBaseApiUrl");
    private String login;
    private String password;

    public TestRailApi(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void addTestResult(TestResultRequestModel testResultModel, String runId, String caseId) {
        String idOfRunInCase = String.format("%s/%s", runId, caseId);
        sendPostRequest(TestRailEndpoint.ADD_RESULT_OF_CASE, idOfRunInCase, testResultModel, TestResultResponseModel.class);
    }

    public SuiteResponseModel addSuite(SuiteRequestModel suiteRequestModel, String projectId) {
        return sendPostRequest(TestRailEndpoint.ADD_SUITE, projectId, suiteRequestModel, SuiteResponseModel.class);
    }

    public RunResponseModel addRun(RunRequestModel runRequestModel, String projectId) {
        return sendPostRequest(TestRailEndpoint.ADD_RUN, projectId, runRequestModel, RunResponseModel.class);
    }

    public SectionResponseModel addSection(SectionRequestModel sectionModel, String projectId) {
        return sendPostRequest(TestRailEndpoint.ADD_SECTION, projectId, sectionModel, SectionResponseModel.class);
    }

    public CaseResponseModel addSteps(CaseRequestModel caseRequestModel, String ownerId) {
        return sendPostRequest(TestRailEndpoint.ADD_CASE, ownerId, caseRequestModel, CaseResponseModel.class);
    }

    public RunResponseModel getRun(String runId) {
        return getModelFromGetRequest(TestRailEndpoint.GET_RUN, runId, RunResponseModel.class);
    }

    public SuiteResponseModel getSuite(String suiteId) {
        return getModelFromGetRequest(TestRailEndpoint.GET_SUITE, suiteId, SuiteResponseModel.class);
    }

    public SectionResponseModel getSection(String sectionId) {
        return getModelFromGetRequest(TestRailEndpoint.GET_SECTION, sectionId, SectionResponseModel.class);
    }

    public CaseResponseModel getCase(String caseId) {
        return getModelFromGetRequest(TestRailEndpoint.GET_CASE, caseId, CaseResponseModel.class);
    }

    public void deleteRun(String runId) {
        sendPostRequest(TestRailEndpoint.DELETE_RUN, runId);
    }

    public void deleteCase(String caseId) {
        sendPostRequest(TestRailEndpoint.DELETE_CASE, caseId);
    }

    public void deleteSection(String sectionId) {
        sendPostRequest(TestRailEndpoint.DELETE_SECTION, sectionId);
    }

    public void deleteSuite(String suiteId) {
        sendPostRequest(TestRailEndpoint.DELETE_SUITE, suiteId);
    }

    private <T> T getModelFromGetRequest(TestRailEndpoint endpoint, String id, Class<T> clazz) {
        try {
            String url = String.format("%s/%s/%s", BASIC_URL, endpoint, id);
            return new ObjectMapper().readValue(executeByUrlGetRequest(url).asString(), clazz);
        } catch (Exception e) {
            Log.error("Problems with creating model", e);
            throw new ExceptionWrapper("Problems with creating model", e);
        }
    }

    private void sendPostRequest(TestRailEndpoint endpoint, String id) {
        String url = String.format("%s/%s/%s", BASIC_URL, endpoint, id);
        executeByUrlPostRequest(url);
    }

    private <T> T sendPostRequest(TestRailEndpoint endpoint, String id, Object model, Class<T> clazz) {
        try {
            String url = String.format("%s/%s/%s", BASIC_URL, endpoint, id);
            return new ObjectMapper().readValue(executeByUrlPostRequest(url, model).asString(), clazz);
        } catch (Exception e) {
            Log.error("Problems with creating model", e);
            throw new ExceptionWrapper("Problems with creating model", e);
        }
    }

    private Response executeByUrlGetRequest(String url) {
        return RestAssured.given()
                .header("Authorization", getAuthorize())
                .header("Content-Type", ContentType.JSON)
                .get(url);
    }

    private void executeByUrlPostRequest(String url) {
        RestAssured.given()
                .header("Authorization", getAuthorize())
                .header("Content-Type", ContentType.JSON)
                .post(url);
    }


    private Response executeByUrlPostRequest(String url, Object model) {
        return RestAssured.given()
                .header("Authorization", getAuthorize())
                .header("Content-Type", ContentType.JSON)
                .body(model)
                .post(url);
    }

    private String getAuthorize() {
        return "Basic " + new String(Base64.getEncoder().encode((login + ":" + password).getBytes()));
    }
}

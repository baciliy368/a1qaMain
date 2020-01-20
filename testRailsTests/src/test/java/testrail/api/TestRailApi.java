package testrail.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import framework.exceptions.ExceptionWrapper;
import framework.utils.Log;
import framework.utils.PropertiesReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.testrail.CaseModel;
import models.testrail.RunModel;
import models.testrail.SectionModel;
import models.testrail.SuiteModel;
import models.testrail.TestResultModel;
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

    public TestResultModel addTestResult(TestResultModel testResultModel, String runId, String caseId) {
        String idOfRunInCase = String.format("%s/%s", runId, caseId);
        return sendPostRequest(TestRailEndpoint.ADD_RESULT_OF_CASE, idOfRunInCase, testResultModel, TestResultModel.class);
    }

    public SuiteModel addSuite(SuiteModel suiteRequestModel, String projectId) {
        return sendPostRequest(TestRailEndpoint.ADD_SUITE, projectId, suiteRequestModel, SuiteModel.class);
    }

    public RunModel addRun(RunModel runRequestModel, String projectId) {
        return sendPostRequest(TestRailEndpoint.ADD_RUN, projectId, runRequestModel, RunModel.class);
    }

    public SectionModel addSection(SectionModel sectionModel, String projectId) {
        return sendPostRequest(TestRailEndpoint.ADD_SECTION, projectId, sectionModel, SectionModel.class);
    }

    public CaseModel addSteps(CaseModel caseRequestModel, String ownerId) {
        return sendPostRequest(TestRailEndpoint.ADD_CASE, ownerId, caseRequestModel, CaseModel.class);
    }

    public RunModel getRun(String runId) {
        return getModelFromGetRequest(TestRailEndpoint.GET_RUN, runId, RunModel.class);
    }

    public SuiteModel getSuite(String suiteId) {
        return getModelFromGetRequest(TestRailEndpoint.GET_SUITE, suiteId, SuiteModel.class);
    }

    public SectionModel getSection(String sectionId) {
        return getModelFromGetRequest(TestRailEndpoint.GET_SECTION, sectionId, SectionModel.class);
    }

    public CaseModel getCase(String caseId) {
        return getModelFromGetRequest(TestRailEndpoint.GET_CASE, caseId, CaseModel.class);
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

    private Response executeByUrlPostRequest(String url) {
        return RestAssured.given()
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

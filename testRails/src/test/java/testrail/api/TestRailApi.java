package testrail.api;

import aquality.selenium.logger.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.utils.PropertiesReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AddTestResultResponseModel;
import models.TestResultModel;
import testrail.enums.TestRailEndpoint;
import java.util.Base64;

public class TestRailApi {
    private static final String PATH_TO_TEST_RAIL_CONFIG = "src/test/resources/test/testRail.properties";
    private static final PropertiesReader customPropertiesReader = new PropertiesReader(PATH_TO_TEST_RAIL_CONFIG);
    private static final String BASIC_URL = customPropertiesReader.getValue("testRailApiUrl");
    private String login;
    private String password;

    public TestRailApi(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AddTestResultResponseModel getTestResult(String runId, String caseId, TestResultModel testResultModel) {
        try {
            Response post = RestAssured.given()
                    .header("Authorization", getAuthorize())
                    .header("Content-Type", ContentType.JSON)
                    .body(testResultModel)
                    .post(String.format("%s/%s/%s/%s", BASIC_URL, TestRailEndpoint.ADD_RESULT_TO_CASE, runId, caseId));
            return new ObjectMapper().readValue(post.asString(), AddTestResultResponseModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            String message = "Problems with creating model";
            Logger.getInstance().error(message);
            throw new IllegalArgumentException(message);
        }
    }

    public void uploadFileByUrl(AddTestResultResponseModel result, byte[] fileByte) {
        RestAssured.given()
                .header("Authorization", getAuthorize())
                .header("Content-Type", "multipart/form-data")
                .multiPart("attachment", "screenshot.png", fileByte)
                .when()
                .post(String.format("%s/%s/%s", BASIC_URL, TestRailEndpoint.ADD_ATTACHMENT_TO_RESULT, result.getId()));
    }

    private String getAuthorize() {
        return "Basic " + new String(Base64.getEncoder().encode((login + ":" + password).getBytes()));
    }
}

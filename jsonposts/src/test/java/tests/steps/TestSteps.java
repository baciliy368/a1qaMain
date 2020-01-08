package tests.steps;

import framework.api.JsonApi;
import framework.utils.ModelGenerator;
import io.restassured.response.Response;
import models.ParamRequestModel;
import org.testng.Assert;

public class TestSteps {

    public static <T> T getModelFromResponse(Response response, Class<T> clazz) {
        return ModelGenerator.getModelByMapping(response, clazz);
    }

    public static void checkResponseJsonType(Response response) {
        Assert.assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"),"response is not in json format");
    }

    public static void checkResponseCode(Response response, int actual) {
        Assert.assertEquals(actual, response.getStatusCode(), String.format("Status code is not %s", actual));
    }

    public static Response getResponse(String url) {
        return new JsonApi(url).executeGetRequest();
    }

    public static Response getResponse(String url, ParamRequestModel params) {
        JsonApi request = new JsonApi(url);
        request.setBodyOfRequest(params);
        return request.executePostRequest();
    }
}
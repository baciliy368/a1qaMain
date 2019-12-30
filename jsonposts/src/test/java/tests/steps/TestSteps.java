package tests.steps;

import framework.api.JsonApi;
import framework.enums.TypeOfConnection;
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

    public static Response getResponse(String url, TypeOfConnection type) {
        return new JsonApi(url, type).executeRequest();
    }

    public static Response getResponse(String url, TypeOfConnection type, ParamRequestModel params) {
        JsonApi request = new JsonApi(url, type);
        request.setBodyOfRequest(params);
        return request.executeRequest();
    }
}
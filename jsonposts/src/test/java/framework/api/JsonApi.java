package framework.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.ParamRequestModel;

public class JsonApi {
    private String url;
    private RequestSpecification request = RestAssured.given();

    public JsonApi(String url) {
        this.url = url;
    }

    public Response executeGetRequest() {
        request.header("Content-Type", "application/json");
        return request.get(url);
    }

    public Response executePostRequest() {
        request.header("Content-Type", "application/json");
        return request.post(url);
    }

    public void setBodyOfRequest(ParamRequestModel paramRequestModel) {
        request.body(paramRequestModel.toString());
    }
}


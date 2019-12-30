package framework.api;

import framework.enums.TypeOfConnection;
import framework.enums.TypesOfSort;
import framework.utils.Log;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.ParamRequestModel;
import java.util.Arrays;

public class JsonApi {
    private String url;
    private TypeOfConnection type;
    private RequestSpecification request = RestAssured.given();

    public JsonApi(String url, TypeOfConnection type) {
        this.type = type;
        this.url = url;
    }

    public Response executeRequest() {
        request.header("Content-Type", "application/json");
        switch (type) {
            case GET:
                return request.get(url);
            case POST:
                return request.post(url);
            default:
                EnumConstantNotPresentException enumConstantNotPresentException
                        = new EnumConstantNotPresentException(TypesOfSort.class, type.toString());
                Log.error(Arrays.toString(enumConstantNotPresentException.getStackTrace()));
                throw enumConstantNotPresentException;
        }
    }

    public void setBodyOfRequest(ParamRequestModel paramRequestModel) {
        request.body(paramRequestModel.toString());
    }
}


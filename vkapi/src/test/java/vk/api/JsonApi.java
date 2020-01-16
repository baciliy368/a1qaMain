package vk.api;

import framework.utils.ModelGenerator;
import framework.utils.PropertiesReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.api.ParamRequestModel;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import vk.enums.EndPoints;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import static io.restassured.RestAssured.given;

public class JsonApi {
    private String url;
    private RequestSpecification request = RestAssured.given();

    public JsonApi(String baseUrl, String token) {
        this.url = String.format("%s%s?access_token=%s&v=%s&",
                baseUrl, "%s", token, PropertiesReader.getValue("VERSION_VK_API"));
    }

    public <T> T executeGet(EndPoints endPoint, ParamRequestModel params, Class<T> clazz) {
        request.header("Content-Type", "application/json");
        try {
            System.out.println(String.format(url, endPoint) + params.getAllParamAsString());
            HttpURLConnection conn = (HttpURLConnection)  new URL(String.format(url, endPoint) + params.getAllParamAsString()).openConnection();
            conn.setRequestMethod("GET");
            JSONObject jsonObject = new JSONObject(IOUtils.toString(conn.getInputStream(), StandardCharsets.UTF_8.name()));
            return ModelGenerator.getModelByMapping(jsonObject.get("response").toString(), clazz);
        } catch (IOException e) {
            return null;
        }
    }

    public void executeGet(EndPoints endPoint, ParamRequestModel params) {
        request.header("Content-Type", "application/json");
        try {
            URL url = new URL(String.format(this.url, endPoint) + params.getAllParamAsString());
            request.get(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Response uploadFileByUrl(String url, File file) {
        return given()
                .param("timestamp", new Date().getTime())
                .multiPart("file", file)
                .accept(ContentType.JSON)
                .when()
                .post(url);
    }
}


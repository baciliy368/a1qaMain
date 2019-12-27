package apiutiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ErrorOfConnectionWithRestApi;
import exceptions.ErrorOfTransformationResponseToText;
import framework.baseelement.BasicApi;
import framework.enums.TypesOfConnection;
import framework.utils.Log;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class JsonApiGet extends BasicApi {
    private String url;

    public JsonApiGet(String url) {
        this.url = url;
    }

    @Override
    public HttpURLConnection getConnection() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod(TypesOfConnection.GET.toString());
            connection.setRequestProperty("Accept", "application/json");
            return connection;
        } catch (IOException e) {
            ErrorOfConnectionWithRestApi errorOfConnectionWithRestApi = new ErrorOfConnectionWithRestApi(e);
            Log.error(errorOfConnectionWithRestApi.getMessage());
            throw errorOfConnectionWithRestApi;
        }
    }

    @Override
    public <T> T getModelByMapping(Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(new URL(url), clazz);
        } catch (Exception e) {
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new ErrorOfTransformationResponseToText(e);
        }
    }

    public <T> T getModelWithDeserialization(File file, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(file, clazz);
        } catch (Exception e) {
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new ErrorOfTransformationResponseToText(e);
        }
    }
}


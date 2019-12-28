package apiutiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import framework.baseelement.BasicApi;
import framework.enums.TypesOfConnection;
import framework.utils.Log;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

public class JsonApiPost extends BasicApi {
    private String url;

    public JsonApiPost(String url) {
        this.url = url;
    }

    @Override
    public HttpURLConnection getConnection() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod(TypesOfConnection.POST.toString());
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            return connection;
        } catch (IOException e) {
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new NullPointerException();
        }
    }

    public HttpURLConnection sendPostRequestWithData(Map<String, String> params) {
        StringBuilder requestData = new StringBuilder();
        HttpURLConnection connection = getConnection();
        try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (requestData.length() != 0) {
                    requestData.append('&');
                }
                requestData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                requestData.append('=');
                requestData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            writer.write(requestData.toString().getBytes(StandardCharsets.UTF_8));
            writer.flush();
            return connection;
        } catch (IOException e) {
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new NullPointerException();
        }
    }

    public <T> T getModelByMappingPostParams(Class<T> clazz, Map<String, String> params) {
        try {
            return new ObjectMapper().readValue(sendPostRequestWithData(params).getInputStream(), clazz);
        } catch (Exception e) {
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new NullPointerException();
        }
    }
}


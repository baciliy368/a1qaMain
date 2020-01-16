package models.api;

import org.json.JSONObject;
import vk.enums.QueryParams;

import java.util.stream.Collectors;

public class ParamRequestModel {

    private JSONObject params = new JSONObject();

    @Override
    public String toString() {
        return params.toString();
    }

    public void addParam(QueryParams name, String param) {
        params.put(name.toString(), param);
    }

    public <T> void addAttachments(String... attachments) {
        params.put("attachments", String.join(",", attachments));
    }

    public String getAllParamAsString() {
        return params.toMap()
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }
}

package models;

import org.json.JSONObject;

public class ParamRequestModel {
    private JSONObject params = new JSONObject();

    @Override
    public String toString() {
        return params.toString();
    }

    public void addParam(String name, String param) {
        params.put(name, param);
    }

    public String getParamByName(String name) {
        return params.get(name).toString();
    }

}

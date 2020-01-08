package models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoOnServerModel {
    private String server;
    private String photo;
    private String hash;

    public String getServer() {
        return server;
    }

    public String getPhoto() {
        return photo;
    }

    public String getHash() {
        return hash;
    }

    public ParamRequestModel getParamsToSave() {
        ParamRequestModel paramRequestModel = new ParamRequestModel();
        paramRequestModel.addParam("server", getServer());
        paramRequestModel.addParam("photo", getPhoto());
        paramRequestModel.addParam("hash", getHash());
        return paramRequestModel;
    }
}

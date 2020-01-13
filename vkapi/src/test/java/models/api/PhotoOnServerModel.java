package models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import vk.enums.QueryParams;

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
        paramRequestModel.addParam(QueryParams.SERVER, getServer());
        paramRequestModel.addParam(QueryParams.PHOTO, getPhoto());
        paramRequestModel.addParam(QueryParams.HASH, getHash());
        return paramRequestModel;
    }
}

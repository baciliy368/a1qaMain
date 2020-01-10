package models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import vk.enums.NamesOfApiParams;

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
        paramRequestModel.addParam(NamesOfApiParams.SERVER, getServer());
        paramRequestModel.addParam(NamesOfApiParams.PHOTO, getPhoto());
        paramRequestModel.addParam(NamesOfApiParams.HASH, getHash());
        return paramRequestModel;
    }
}

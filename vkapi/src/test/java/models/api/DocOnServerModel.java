package models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import vk.enums.QueryParams;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocOnServerModel {
    private String file;

    public String getFile() {
        return file;
    }

    public ParamRequestModel getParamsToSave() {
        ParamRequestModel paramRequestModel = new ParamRequestModel();
        paramRequestModel.addParam(QueryParams.FILE, getFile());
        return paramRequestModel;
    }
}

package models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocOnServerModel {
    private String file;

    public String getFile() {
        return file;
    }

    public ParamRequestModel getParamsToSave() {
        ParamRequestModel paramRequestModel = new ParamRequestModel();
        System.out.println(getFile());
        paramRequestModel.addParam("file", getFile());
        return paramRequestModel;
    }
}

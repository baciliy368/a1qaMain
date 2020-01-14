package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddTestResultResponseModel {
    private String id;

    public String getId() {
        return id;
    }
}

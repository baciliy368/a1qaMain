package models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadServer {
    @JsonProperty("upload_url")
    private String uploadUrl;

    public String getUploadUrl() {
        return uploadUrl;
    }
}

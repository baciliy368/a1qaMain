package models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerOfUploadingFileModel {
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

}

package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoModel {
    @JsonProperty("id")
    private String id;
    @JsonProperty("owner_id")
    private String ownerId;

    @Override
    public String toString() {
        return String.format("photo%s_%s", ownerId, id);
    }
}

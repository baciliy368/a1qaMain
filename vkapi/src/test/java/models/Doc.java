package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Doc {

    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("owner_id")
    private Long ownerId;

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    @Override
    public String toString() {
        return String.format("doc%s_%s", ownerId, id);
    }
}

package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Doc {
    private String type;
    private Long id;
    @JsonProperty("owner_id")
    private Long ownerId;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("doc%s_%s", ownerId, id);
    }
}

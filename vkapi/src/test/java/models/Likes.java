package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Likes {
    @JsonProperty("liked")
    private int likes;

    public boolean isLiked() {
        return likes == 1;
    }
}

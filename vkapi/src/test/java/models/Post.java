package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("post_id")
public class Post {
    @JsonProperty("post_id")
    private String postId;

    public String getPostId() {
        return postId;
    }
}

package models.postsmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostAnswerWithPostRequest {
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;
    @JsonProperty("id")
    private String id;
    @JsonProperty("userId")
    private String userId;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }
}

package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestResultModel {
    @JsonProperty("status_id")
    private int statusId;
    private String comment;

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

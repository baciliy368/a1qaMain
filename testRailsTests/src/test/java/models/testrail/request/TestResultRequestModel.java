package models.testrail.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.javers.core.metamodel.annotation.DiffIgnore;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestResultRequestModel {
    @JsonProperty("status_id")
    private String statusId;
    private String comment;

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatusId() {
        return statusId;
    }

    public String getComment() {
        return comment;
    }
}

package models.testrail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.javers.core.metamodel.annotation.DiffIgnore;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestResultModel {
    @DiffIgnore
    private String id;
    @JsonProperty("status_id")
    private String statusId;
    private String comment;

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusId() {
        return statusId;
    }

    public String getComment() {
        return comment;
    }
}

package models.testrail.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.javers.core.metamodel.annotation.DiffIgnore;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseResponseModel {
    @JsonProperty("custom_steps_separated")
    private StepResponseModel[] steps;
    private String title;
    @DiffIgnore
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StepResponseModel[] getSteps() {
        return steps;
    }

    public void setSteps(StepResponseModel[] steps) {
        this.steps = steps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

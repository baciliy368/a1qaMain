package models.testrail.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.javers.core.metamodel.annotation.DiffIgnore;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseRequestModel {
    @JsonProperty("custom_steps_separated")
    private StepRequestModel[] steps;
    private String title;

    public StepRequestModel[] getSteps() {
        return steps;
    }

    public void setSteps(List<StepRequestModel> steps) {
        this.steps = (StepRequestModel[]) steps.toArray();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

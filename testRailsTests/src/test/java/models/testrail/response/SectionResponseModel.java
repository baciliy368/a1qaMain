package models.testrail.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.javers.core.metamodel.annotation.DiffIgnore;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SectionResponseModel {
    private String name;
    @JsonProperty("suite_id")
    private String suiteId;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(String suiteId) {
        this.suiteId = suiteId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

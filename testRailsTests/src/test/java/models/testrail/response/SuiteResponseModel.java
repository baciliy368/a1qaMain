package models.testrail.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.javers.core.metamodel.annotation.DiffIgnore;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuiteResponseModel {
    private String description;
    private String id;
    private String name;

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

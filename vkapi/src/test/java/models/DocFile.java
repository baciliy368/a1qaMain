package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocFile {
    @JsonProperty("doc")
    private Doc doc;

    public Doc getDoc() {
        return doc;
    }
}

package models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("catchPhrase")
    private String catchPhrase;
    @JsonProperty("bs")
    private String bs;

}

package models.usersmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {
    @JsonProperty("name")
    private String name;
    @JsonProperty("catchPhrase")
    private String catchPhrase;
    @JsonProperty("bs")
    private String bs;

}

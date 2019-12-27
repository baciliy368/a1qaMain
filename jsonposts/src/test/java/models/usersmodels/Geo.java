package models.usersmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geo {
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}

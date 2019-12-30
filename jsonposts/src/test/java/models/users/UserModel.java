package models.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import framework.utils.Log;
import java.util.Arrays;

public class UserModel {
    private int id;
    private String name;
    private String username;
    private String email;
    @JsonProperty("address")
    private AddressModel addressModel;
    private String phone;
    private String website;
    @JsonProperty("company")
    private CompanyModel companyModel;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public CompanyModel getCompanyModel() {
        return companyModel;
    }

    public boolean equals(UserModel modelToCompare) {
        JsonMapper jsonMapper = new JsonMapper();
        try {
            return jsonMapper.writeValueAsString(this).equals(jsonMapper.writeValueAsString(modelToCompare));
        } catch (JsonProcessingException e) {
            Log.info(Arrays.toString(e.getStackTrace()));
            throw new NullPointerException();
        }
    }
}

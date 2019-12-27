package models.usersmodels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import exceptions.ErrorOfTransformationResponseToText;
import framework.utils.Log;
import java.util.Arrays;

public class UserModel {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

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

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Company getCompany() {
        return company;
    }

    public boolean equals(UserModel modelToCompare) {
        JsonMapper jsonMapper = new JsonMapper();
        try {
            return jsonMapper.writeValueAsString(this).equals(jsonMapper.writeValueAsString(modelToCompare));
        } catch (JsonProcessingException e) {
            Log.info(Arrays.toString(e.getStackTrace()));
            throw new ErrorOfTransformationResponseToText(e);
        }
    }
}

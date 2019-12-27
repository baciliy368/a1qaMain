package testpackage.steps;

import apiutiles.JsonApiGet;
import framework.utils.PropertiesReader;
import models.usersmodels.UserModel;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.net.HttpURLConnection;

public class UserStepsManager {
    private final String BODY_URL_TO_GET_USERS = PropertiesReader.getValue("BODY_URL_TO_GET_USERS");
    private final String BODY_URL_TO_GET_USER_5 = PropertiesReader.getValue("BODY_URL_TO_GET_USER_5");

    public void usersStep() {
        JsonApiGet getUsers = new JsonApiGet(BODY_URL_TO_GET_USERS);
        UserModel[] allUsers = getUsers.getModelByMapping(UserModel[].class);
        UserModel userModel5 = getUserById(allUsers, 5);
        UserModel actualUser = getUsers.getModelWithDeserialization(new File(PropertiesReader.getValue("PATH_TO_USER_FILE")), UserModel.class);
        SoftAssert softAssertStepFive = new SoftAssert();
        softAssertStepFive.assertEquals(HttpURLConnection.HTTP_OK, getUsers.getResponseCode(), "response code is not ");
        softAssertStepFive.assertEquals("application/json; charset=utf-8",
                getUsers.getConnection().getHeaderField("Content-Type"), "it is not .json file");
        softAssertStepFive.assertTrue(userModel5 != null && userModel5.equals(actualUser), "users are not much");
        softAssertStepFive.assertAll();
    }

    public void user5Step() {
        JsonApiGet getUsers = new JsonApiGet(BODY_URL_TO_GET_USER_5);
        UserModel user = getUsers.getModelByMapping(UserModel.class);
        UserModel actualUser = getUsers.getModelWithDeserialization(new File(PropertiesReader.getValue("PATH_TO_USER_FILE")), UserModel.class);
        SoftAssert softAssertStepFive = new SoftAssert();
        softAssertStepFive.assertEquals(HttpURLConnection.HTTP_OK, getUsers.getResponseCode(), "response code is not ");
        softAssertStepFive.assertTrue(user.equals(actualUser), "users are not much");
        softAssertStepFive.assertAll();
    }

    private UserModel getUserById(UserModel[] userModels, int id) {
        for (UserModel userModel : userModels) {
            if (userModel.getId() == id) {
                return userModel;
            }
        }
        return null;
    }
}

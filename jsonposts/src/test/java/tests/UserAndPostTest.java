package tests;

import framework.enums.TypesOfSort;
import framework.utils.Log;
import framework.utils.ModelGenerator;
import framework.utils.PropertiesReader;
import io.restassured.response.Response;
import models.ParamRequestModel;
import models.posts.PostModel;
import models.users.UserModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.enums.EndPoint;
import tests.steps.TestSteps;
import tests.utiles.PostsManager;
import java.io.File;
import java.net.HttpURLConnection;
import java.util.Arrays;

public class UserAndPostTest extends BaseTest {
    private static final int NUMBER_OF_RANDOM_SYMBOLS_IN_STRING = 10;
    private static final String TITLE = RandomStringUtils.randomAlphabetic(NUMBER_OF_RANDOM_SYMBOLS_IN_STRING);
    private static final String BODY = RandomStringUtils.randomAlphabetic(NUMBER_OF_RANDOM_SYMBOLS_IN_STRING);
    public static final String ID = "1";
    private static final String BASE_URL_TO_API_REQUEST = PropertiesReader.getValue("BASE_URL_TO_API_REQUEST");

    @Test
    public void testJsonPostAndUserTest() {
        Log.step(1, "Test GET request to take all posts");
        Response responseOfFirstTest = TestSteps.getResponse(BASE_URL_TO_API_REQUEST + EndPoint.POSTS);
        PostModel[] allPostsModelsFromRequest = TestSteps.getModelFromResponse(responseOfFirstTest, PostModel[].class);
        TestSteps.checkResponseCode(responseOfFirstTest, HttpURLConnection.HTTP_OK);
        Assert.assertTrue(PostsManager.isArrayOfPostsSortedById(TypesOfSort.ASCENDING, allPostsModelsFromRequest));

        Log.step(2, "Test GET request to take post with id");
        int numberOfPostStep2 = 99;
        Response responseOfSecondStep = TestSteps.getResponse(BASE_URL_TO_API_REQUEST + EndPoint.POSTS + numberOfPostStep2);
        TestSteps.checkResponseCode(responseOfSecondStep, HttpURLConnection.HTTP_OK);
        PostModel post99 = TestSteps.getModelFromResponse(responseOfSecondStep, PostModel.class);
        SoftAssert softAssertStepTwo = new SoftAssert();
        softAssertStepTwo.assertEquals(10, post99.getUserId(), "userId are not match");
        softAssertStepTwo.assertEquals(numberOfPostStep2, post99.getId(), "Id are not match");
        softAssertStepTwo.assertNotNull(post99.getBody(), "Body is empty");
        softAssertStepTwo.assertNotNull(post99.getTitle(), "Title is empty");
        softAssertStepTwo.assertAll();

        Log.step(3, "Test GET request to take posts with id 150");
        int numberOfPostStep3 = 150;
        Response responseOfThirdStep = TestSteps.getResponse(BASE_URL_TO_API_REQUEST + EndPoint.POSTS + numberOfPostStep3);
        TestSteps.checkResponseCode(responseOfThirdStep, HttpURLConnection.HTTP_NOT_FOUND);
        System.out.println();
        Assert.assertEquals("{}", responseOfThirdStep.asString(), "responseOfFifthStep is not equals {}");

        Log.step(4, "Test POST request to create post");
        ParamRequestModel paramRequestModel = new ParamRequestModel();
        paramRequestModel.addParam("title", TITLE);
        paramRequestModel.addParam("body", BODY);
        paramRequestModel.addParam("userId", ID);
        Response responseOfForthStep = TestSteps.getResponse(BASE_URL_TO_API_REQUEST + EndPoint.POSTS, paramRequestModel);
        PostModel postCreatedByTest = TestSteps.getModelFromResponse(responseOfForthStep, PostModel.class);
        SoftAssert softAssertStepFour = new SoftAssert();
        softAssertStepFour.assertEquals(postCreatedByTest.getTitle(), paramRequestModel.getParamByName("title"), "Titles are not match");
        softAssertStepFour.assertEquals(postCreatedByTest.getBody(), paramRequestModel.getParamByName("body"), "Body are not match");
        softAssertStepFour.assertEquals(postCreatedByTest.getUserId(), paramRequestModel.getParamByName("userId"), "UserId are not match");
        softAssertStepFour.assertNotNull(responseOfForthStep.jsonPath().get("id").toString(),  "Id is not expected");

        Log.step(5, "Test GET request to take all users");
        int idOfUser = 5;
        Response responseOfFifthStep = TestSteps.getResponse(BASE_URL_TO_API_REQUEST + EndPoint.USERS);
        TestSteps.checkResponseCode(responseOfFifthStep, HttpURLConnection.HTTP_OK);
        TestSteps.checkResponseJsonType(responseOfFifthStep);
        UserModel[] allUsers = TestSteps.getModelFromResponse(responseOfFifthStep, UserModel[].class);
        UserModel userModelStep5 = Arrays.stream(allUsers).filter(user -> user.getId() == idOfUser).findFirst().orElse(null);
        UserModel actualUser = ModelGenerator.getModelByMapping(new File(PropertiesReader.getValue("PATH_TO_USER_FILE")), UserModel.class);
        Assert.assertTrue(userModelStep5 != null && userModelStep5.equals(actualUser), "users are not much");

        Log.step(6, "Test GET request to take user with id 5");
        Response responseOfSixStep = TestSteps.getResponse(BASE_URL_TO_API_REQUEST + EndPoint.USERS + idOfUser);
        UserModel userModelStep6 = TestSteps.getModelFromResponse(responseOfSixStep, UserModel.class);
        TestSteps.checkResponseCode(responseOfSixStep, HttpURLConnection.HTTP_OK);
        Assert.assertTrue(userModelStep6 != null && userModelStep6.equals(userModelStep5), "users are not much");
    }
}
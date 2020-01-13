package test;

import aquality.selenium.browser.BrowserManager;
import framework.utils.Log;
import framework.utils.ModelGenerator;
import framework.utils.PropertiesReader;
import models.Post;
import models.api.ParamRequestModel;
import models.test.UserModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.LoginPage;
import pageobject.NewsPage;
import pageobject.UserPage;
import test.steps.TestStepsVk;
import vk.api.VkUserActions;
import vk.enums.QueryParams;
import java.io.File;

public class TestVisibilityOfUserPostForUnknownUser extends BaseTest {
    private static final int NUMBER_OF_RANDOM_SYMBOLS_IN_STRING = 10;
    private static final String RAND_STRING = RandomStringUtils.randomAlphabetic(NUMBER_OF_RANDOM_SYMBOLS_IN_STRING);
    private static final File CONFIG_USER_A = new File(PropertiesReader.getValue("USER_A"));

    @Test
    public void testPostUnknownUser() {
        Log.step(1, "Open main page");
        BrowserManager.getBrowser().goTo(MAIN_PAGE);

        Log.step(2, "Log in");
        LoginPage mainPage = new LoginPage();
        UserModel userModel = ModelGenerator.getModelByMapping(CONFIG_USER_A, UserModel.class);
        mainPage.getLoginForm().loginUser(userModel);

        Log.step(3, "Open user page");
        NewsPage newsPage = new NewsPage();
        newsPage.getRightBarForm().clickMainUserPage();

        Log.step(4, "Get url of page");
        UserPage userPage = new UserPage();
        String urlOfPageUserA = TestStepsVk.getUrlOfPage(userPage);

        Log.step(5, "Get id of user");
        String idOfUserA = userPage.getIdOfUser();

        Log.step(6, "Creating post using vkAPI");
        VkUserActions stepsUserA = new VkUserActions(userModel.getToken());
        ParamRequestModel paramToCreatePostWithText = new ParamRequestModel();
        paramToCreatePostWithText.addParam(QueryParams.MESSAGE, RAND_STRING);
        Post post = stepsUserA.createPost(paramToCreatePostWithText);

        Log.step(7, "log out");
        userPage.logOut();

        Log.step(8, "Open main page");
        BrowserManager.getBrowser().goTo(MAIN_PAGE);

        Log.step(9, "Open user page");
        BrowserManager.getBrowser().goTo(urlOfPageUserA);

        Log.step(10, "check is post created");
        TestStepsVk.checkOwnerOfPost(idOfUserA, post);
        TestStepsVk.checkIdOfPost(post);
        TestStepsVk.checkTextOfPost(RAND_STRING, post);

        Log.step(11, "check Quick Login Form ");
        Assert.assertTrue(userPage.getQuickLoginForm().isFormDisplayed(), "Quick Login Form is not displayed on page");
    }
}

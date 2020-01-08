package testpackage;

import aquality.selenium.browser.BrowserManager;
import framework.browser.Driver;
import framework.utils.Log;
import framework.utils.ModelGenerator;
import framework.utils.PropertiesReader;
import models.Post;
import models.api.ParamRequestModel;
import models.test.UserModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pageobject.LoginPage;
import pageobject.NewsPage;
import pageobject.UserPage;
import testpackage.steps.TestSteps;
import vk.api.VkUserActions;

import java.io.File;

public class TestCase2 extends BaseTest {
    private static final int NUMBER_OF_RANDOM_SYMBOLS_IN_STRING = 10;
    private static final String RAND_STRING = RandomStringUtils.randomAlphabetic(NUMBER_OF_RANDOM_SYMBOLS_IN_STRING);
    private static final File CONFIG_USER_A = new File(PropertiesReader.getValue("USER_A"));
    private static final File CONFIG_USER_B = new File(PropertiesReader.getValue("USER_A"));

    @Test
    public void testPostAndLikesFromOtherUser() {
        UserModel userModelA = ModelGenerator.getModelByMapping(CONFIG_USER_A, UserModel.class);
        UserModel userModelB = ModelGenerator.getModelByMapping(CONFIG_USER_B, UserModel.class);

        Log.step(1, "Open main page");
        BrowserManager.getBrowser().goTo(MAIN_PAGE);

        Log.step(2, "Log in userA");
        LoginPage mainPage = new LoginPage();
        mainPage.getLoginForm().loginUser(userModelA);

        Log.step(3, "Open user page");
        NewsPage newsPage = new NewsPage();
        newsPage.getRightBarForm().clickMainUserPage();

        Log.step(4, "Get url of page");
        UserPage userPage = new UserPage();
        String urlOfPageUserA = Driver.getUrlOfPage(userPage);

        Log.step(5, "Get id of user");
        String idOfUserA = userPage.getIdOfUser();

        Log.step(6, "Creating post using vkAPI");
        VkUserActions firstUserActions = new VkUserActions(userModelA.getToken());
        ParamRequestModel paramToCreatePostWithText = new ParamRequestModel();
        paramToCreatePostWithText.addParam("message", RAND_STRING);
        Post post = firstUserActions.createPost(paramToCreatePostWithText);

        Log.step(7, "Click like on post");
        TestSteps.clickLikeOnPost(post);

        Log.step(8, "log out");
        userPage.logOut();

        Log.step(9, "Open main page");
        BrowserManager.getBrowser().goTo(MAIN_PAGE);

        Log.step(10, "Log in userB");
        LoginPage mainPageSecondUser = new LoginPage();
        mainPageSecondUser.getLoginForm().loginUser(userModelB);

        Log.step(11, "Open userA page");
        BrowserManager.getBrowser().goTo(urlOfPageUserA);

        Log.step(12, "check is post created");
        TestSteps.checkOwnerOfPost(idOfUserA, post);
        TestSteps.checkIdOfPost(post);
        TestSteps.checkTextOfPost(RAND_STRING, post);

        Log.step(14, "check likes on post");
        firstUserActions.checkLikeOnPost(idOfUserA, post.getPostId());
    }
}

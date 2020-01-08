package testpackage;

import aquality.selenium.browser.BrowserManager;
import framework.utils.ImageComparator;
import framework.utils.Log;
import framework.utils.ModelGenerator;
import framework.utils.PropertiesReader;
import models.PhotoModel;
import models.Post;
import models.api.ParamRequestModel;
import models.test.UserModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.LoginPage;
import pageobject.NewsPage;
import pageobject.UserPage;
import testpackage.steps.TestSteps;
import vk.api.VkUserActions;

import java.io.File;

public class TestCase1 extends BaseTest {
    private static final int NUMBER_OF_RANDOM_SYMBOLS_IN_STRING = 10;
    private static final String RAND_STRING = RandomStringUtils.randomAlphabetic(NUMBER_OF_RANDOM_SYMBOLS_IN_STRING);
    private static final File CONFIG_USER = new File(PropertiesReader.getValue("USER_A"));
    private static final File PHOTO = new File(PropertiesReader.getValue("PIC_A"));

    @Test
    public void testPostsActions() {
        UserModel userModel = ModelGenerator.getModelByMapping(CONFIG_USER, UserModel.class);
        VkUserActions userApiActions = new VkUserActions(userModel.getToken());
        Log.step(1, "Open main page");
        BrowserManager.getBrowser().goTo(MAIN_PAGE);

        Log.step(2, "Log in");
        LoginPage mainPage = new LoginPage();
        mainPage.getLoginForm().loginUser(userModel);

        Log.step(3, "Open user page");
        NewsPage newsPage = new NewsPage();
        newsPage.getRightBarForm().clickMainUserPage();

        Log.step(4, "Creating post using vkAPI");
        UserPage userPage = new UserPage();
        VkUserActions stepsUserA = new VkUserActions(userModel.getToken());
        ParamRequestModel paramToCreatePostWithText = new ParamRequestModel();
        paramToCreatePostWithText.addParam("message", RAND_STRING);
        Post post = stepsUserA.createPost(paramToCreatePostWithText);

        Log.step(5,"Check is post created");
        TestSteps.checkTextOfPost(RAND_STRING, post);

        Log.step(6, "Edit post, insert picture");
        PhotoModel photo = stepsUserA.uploadPhoto(userPage.getIdOfUser(), PHOTO);
        ParamRequestModel paramRequestModel = new ParamRequestModel();
        paramRequestModel.addParam("post_id", post.getPostId());
        paramRequestModel.addParam("message", RAND_STRING.toUpperCase());
        paramRequestModel.addAttachments(photo.toString());
        post = userApiActions.editWall(paramRequestModel);

        Log.step(7, "Check is post edit with image and text");
        TestSteps.checkTextOfPost(RAND_STRING.toUpperCase(), post);
        Assert.assertTrue(ImageComparator.compareFileByUrl(PHOTO, userPage.getLinkOnImg(photo.toString())));

        Log.step(8, "Add comment to post");
        ParamRequestModel paramsToAddComment = new ParamRequestModel();
        paramsToAddComment.addParam("owner_id", userPage.getIdOfUser());
        paramsToAddComment.addParam("post_id", post.getPostId());
        paramsToAddComment.addParam("message", RAND_STRING);
        stepsUserA.addCommentToPost(paramsToAddComment);

        Log.step(9, "Check comment");
        TestSteps.checkCommentAdded(post);

        Log.step(10, "Click like on post");
        TestSteps.clickLikeOnPost(post);

        Log.step(11, "Check like on post");
        stepsUserA.checkLikeOnPost(userPage.getIdOfUser(), post.getPostId());

        Log.step(12, "Delete post");
        stepsUserA.deletePost(userPage.getIdOfUser(), post.getPostId());

        Log.step(13, "Check delete post");
        TestSteps.checkIsPostDeleted(post);
    }
}


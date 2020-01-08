package testpackage;

import aquality.selenium.browser.BrowserManager;
import framework.utils.Log;
import framework.utils.ModelGenerator;
import framework.utils.PropertiesReader;
import models.Doc;
import models.Post;
import models.api.ParamRequestModel;
import models.test.UserModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.LoginPage;
import pageobject.NewsPage;
import pageobject.UserPage;
import testpackage.steps.TestSteps;
import vk.api.VkUserActions;
import java.io.File;

public class TestCase4 extends BaseTest {

    private static final int NUMBER_OF_RANDOM_SYMBOLS_IN_STRING = 10;
    private static final String RAND_STRING = RandomStringUtils.randomAlphabetic(NUMBER_OF_RANDOM_SYMBOLS_IN_STRING);
    private static final File TEXT_FILE = new File(PropertiesReader.getValue("TEXT_FILE"));
    private static final File CONFIG_USER = new File(PropertiesReader.getValue("USER_A"));

    @Test
    public void testPostWithFile() {
        UserModel userModel = ModelGenerator.getModelByMapping(CONFIG_USER, UserModel.class);
        Log.step(1, "Open main page");
        BrowserManager.getBrowser().goTo(MAIN_PAGE);

        Log.step(2, "Log in");
        LoginPage mainPage = new LoginPage();
        mainPage.getLoginForm().loginUser(userModel);

        Log.step(3, "Open user page");
        NewsPage newsPage = new NewsPage();
        newsPage.getRightBarForm().clickMainUserPage();
        UserPage userPage = new UserPage();

        Log.step(4, "Creating post using vkAPI");
        VkUserActions stepsUserB = new VkUserActions(userModel.getToken());
        ParamRequestModel paramToCreatePostWithText = new ParamRequestModel();
        paramToCreatePostWithText.addParam("message", RAND_STRING);
        Post post = stepsUserB.createPost(paramToCreatePostWithText);

        Log.step(5, "Edit post, insert picture");
        Doc doc = stepsUserB.uploadDoc(userPage.getIdOfUser(), TEXT_FILE);
        ParamRequestModel paramRequestModel = new ParamRequestModel();
        paramRequestModel.addParam("post_id", post.getPostId());
        paramRequestModel.addParam("message", RAND_STRING.toUpperCase());
        paramRequestModel.addAttachments(doc.toString());
        post = stepsUserB.editWall(paramRequestModel);

        Log.step(6, "Check edit of post");
        Assert.assertEquals(TEXT_FILE.getName(), userPage.getPostsForm().getPost(post.getPostId()).getNameOfFileInPost(),
                "names of file are not match");
        TestSteps.checkTextOfPost(RAND_STRING.toUpperCase(), post);

        Log.step(7, "Add comment to post");
        ParamRequestModel paramOfComment = new ParamRequestModel();
        paramOfComment.addParam("owner_id", userPage.getIdOfUser());
        paramOfComment.addParam("post_id", post.getPostId());
        paramOfComment.addParam("message", RAND_STRING);
        stepsUserB.addCommentToPost(paramOfComment);

        Log.step(8, "Check comment");
        Assert.assertTrue(userPage.getPostsForm().getPost(post.getPostId()).isCommentAdded(), "Comment is not added");

        Log.step(9, "Click like on post");
        TestSteps.clickLikeOnPost(post);

        Log.step(10, "Check like on post");
        stepsUserB.checkLikeOnPost(userPage.getIdOfUser(), post.getPostId());

        Log.step(11, "Delete post");
        stepsUserB.deletePost(userPage.getIdOfUser(), post.getPostId());

        Log.step(12, "Check delete post");
        TestSteps.checkIsPostDeleted(post);
    }
}

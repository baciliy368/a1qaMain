package test;

import aquality.selenium.browser.BrowserManager;
import framework.utils.ImageManager;
import framework.utils.Log;
import framework.utils.PropertiesReader;
import models.PhotoModel;
import models.Post;
import models.api.ParamRequestModel;
import models.test.UserModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobject.LoginPage;
import pageobject.NewsPage;
import pageobject.UserPage;
import test.dataprovider.DataProviderForVkApiTest;
import vk.api.JsonApi;
import vk.api.VkUserActions;
import vk.enums.EndPoints;
import vk.enums.QueryParams;
import java.io.File;

public class TestPostsWithFormatsOfPictures extends BaseTest {
    private static final int NUMBER_OF_RANDOM_SYMBOLS_IN_STRING = 10;
    private static final String RAND_STRING = RandomStringUtils.randomAlphabetic(NUMBER_OF_RANDOM_SYMBOLS_IN_STRING);
    private static final String BODY_OF_API_URL = PropertiesReader.getValue("VK_PATTERN");
    private static final String MAIN_PAGE = PropertiesReader.getValue("VK_COM");
    private static final double  EXPECTED_PERCENTAGE_OF_COMPARING_IMAGES = Double.parseDouble(PropertiesReader.getValue("EXPECTED_PERCENTAGE_OF_COMPARING_IMAGES"));

    @BeforeMethod
    public void prepareBrowser() {
        BrowserManager.getBrowser().maximize();
    }

    @Test(dataProvider = "UserAndPictureDataProvider", dataProviderClass = DataProviderForVkApiTest.class)
    public void testImgFormatUploading(UserModel user, File picFile) {
        JsonApi userApiActions = new JsonApi(BODY_OF_API_URL, user.getToken());
        Log.step(1, "Open login page");
        BrowserManager.getBrowser().goTo(MAIN_PAGE);

        Log.step(2, "Log in");
        LoginPage mainPage = new LoginPage();
        mainPage.getLoginForm().loginUser(user);

        Log.step(3, "Open user page");
        NewsPage newsPage = new NewsPage();
        newsPage.getRightBarForm().clickMainUserPage();
        UserPage userPage = new UserPage();

        Log.step(4, "Creating post with text and photo");
        VkUserActions secondUserActions = new VkUserActions(user.getToken());
        PhotoModel photo = secondUserActions.uploadPhoto(userPage.getIdOfUser(), picFile);
        ParamRequestModel paramRequestModel = new ParamRequestModel();
        paramRequestModel.addParam(QueryParams.MESSAGE, RAND_STRING.toUpperCase());
        paramRequestModel.addAttachments(photo.toString());
        Post post = userApiActions.executeGet(EndPoints.WALL_POST, paramRequestModel, Post.class);

        Log.step(5, "Check is post created");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(RAND_STRING.toUpperCase(), userPage.getPostsForm().getPost(post.getPostId()).getText(), "post text is not match");
        softAssert.assertTrue(ImageManager.compareImage(ImageManager.getBufferedImage(picFile),
                ImageManager.getBufferedImage(userPage.getLinkOnImg(photo.toString())), EXPECTED_PERCENTAGE_OF_COMPARING_IMAGES),
                "pictures are not match");
        softAssert.assertAll();
    }

    @AfterMethod
    public void closeBrowser() {
        BrowserManager.getBrowser().quit();
    }
}

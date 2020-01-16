package test.steps;

import aquality.selenium.browser.BrowserManager;
import aquality.selenium.forms.Form;
import framework.utils.Log;
import models.Post;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import pageobject.PostForm;
import pageobject.UserPage;
import java.util.Arrays;

public class TestStepsVk {

    public static void clickLikeOnPost(Post post) {
        new UserPage().getPostsForm().getPost(post.getPostId()).clickLike();
    }

    public static void checkIsPostDeleted(Post post) {
        Assert.assertTrue(new UserPage().getPostsForm().getPost(post.getPostId()).isFormDisplayed(),"post is not deleted");
    }

    public static void checkTextOfPost(String mustBe, Post post) {
        PostForm postForm = new UserPage().getPostsForm().getPost(post.getPostId());
        Assert.assertEquals(mustBe, postForm.getText(), "texts are not match");
    }

    public static void checkOwnerOfPost(String ownerId, Post post) {
        PostForm postForm = new UserPage().getPostsForm().getPost(post.getPostId());
        Assert.assertEquals(ownerId, postForm.getOwnerId(), "texts are not match");
    }

    public static String getUrlOfPage(Form form) {
        if (form.isFormDisplayed()) {
            return BrowserManager.getBrowser().getCurrentUrl();
        } else {
            NoSuchElementException noSuchElementException = new NoSuchElementException("form is not displayed");
            Log.error(Arrays.toString(noSuchElementException.getStackTrace()));
            throw noSuchElementException;
        }
    }

    public static void checkIdOfPost(Post post) {
        PostForm postForm = new UserPage().getPostsForm().getPost(post.getPostId());
        Assert.assertEquals(post.getPostId(), postForm.getId(), "texts are not match");
    }

    public static void checkCommentAdded(Post post) {
        Assert.assertTrue(new UserPage().getPostsForm().getPost(post.getPostId()).isCommentAdded(), "Comment is not added");
    }
}

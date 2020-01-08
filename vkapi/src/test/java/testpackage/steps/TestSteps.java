package testpackage.steps;

import models.Post;
import org.testng.Assert;
import pageobject.PostOnPage;
import pageobject.UserPage;

public class TestSteps {

    public static void clickLikeOnPost(Post post) {
        new UserPage().getPostsForm().getPost(post.getPostId()).clickLike();
    }

    public static void checkIsPostDeleted(Post post) {
        Assert.assertTrue(new UserPage().getPostsForm().isPostDeleted(post.getPostId()), "post is not deleted");
    }

    public static void checkTextOfPost(String mustBe, Post post) {
        PostOnPage postOnPage = new UserPage().getPostsForm().getPost(post.getPostId());
        Assert.assertEquals(mustBe, postOnPage.getText(), "texts are not match");
    }

    public static void checkOwnerOfPost(String ownerId, Post post) {
        PostOnPage postOnPage = new UserPage().getPostsForm().getPost(post.getPostId());
        Assert.assertEquals(ownerId, postOnPage.getOwnerId(), "texts are not match");
    }

    public static void checkIdOfPost(Post post) {
        PostOnPage postOnPage = new UserPage().getPostsForm().getPost(post.getPostId());
        Assert.assertEquals(post.getPostId(), postOnPage.getId(), "texts are not match");
    }

    public static void checkCommentAdded(Post post) {
        Assert.assertTrue(new UserPage().getPostsForm().getPost(post.getPostId()).isCommentAdded(), "Comment is not added");
    }
}

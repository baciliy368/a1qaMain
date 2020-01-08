package vk.api;

import framework.utils.PropertiesReader;
import models.Doc;
import models.Likes;
import models.PhotoModel;
import models.Post;
import models.api.ParamRequestModel;
import org.testng.Assert;
import vk.enums.EndPoints;
import vk.utils.DocUploader;
import vk.utils.PhotoUploader;
import java.io.File;

public class VkUserActions {
    private static final String BODY_OF_API_URL = PropertiesReader.getValue("VK_PATTERN");
    private JsonApi userApiActions;

    public VkUserActions(String token) {
        this.userApiActions = new JsonApi(BODY_OF_API_URL, token);
    }

    public void checkLikeOnPost(String idOfUser, String itemId) {
        ParamRequestModel paramsToCheckIsPostHasLike = new ParamRequestModel();
        paramsToCheckIsPostHasLike.addParam("type", "post");
        paramsToCheckIsPostHasLike.addParam("owner_id", idOfUser);
        paramsToCheckIsPostHasLike.addParam("item_id", itemId);
        Likes likes = userApiActions.executeGet(EndPoints.LIKES_IS_LIKED, paramsToCheckIsPostHasLike, Likes.class);
        Assert.assertTrue(likes.isLiked(), "post is not liked");
    }

    public void deletePost(String ownerId, String postId) {
        ParamRequestModel paramsToDeletePost = new ParamRequestModel();
        paramsToDeletePost.addParam("owner_id", ownerId);
        paramsToDeletePost.addParam("post_id", postId);
        userApiActions.executeGet(EndPoints.WALL_DELETE, paramsToDeletePost);
    }

    public Doc uploadDoc(String idOfUser, File doc) {
        DocUploader docUploader = new DocUploader(userApiActions);
        ParamRequestModel paramToGetUrlToUploadPhotoForWall = new ParamRequestModel();
        paramToGetUrlToUploadPhotoForWall.addParam("user_id", idOfUser);
        return docUploader.getDocOnServer(doc, paramToGetUrlToUploadPhotoForWall);
    }

    public PhotoModel uploadPhoto(String idOfUser, File photo) {
        PhotoUploader photoUploader = new PhotoUploader(userApiActions);
        ParamRequestModel paramToGetUrlToUploadPhotoForWall = new ParamRequestModel();
        paramToGetUrlToUploadPhotoForWall.addParam("user_id",idOfUser);
        return photoUploader.getPhotoOnServer(photo, paramToGetUrlToUploadPhotoForWall);

    }

    public Post editWall(ParamRequestModel params) {
        return userApiActions.executeGet(EndPoints.WALL_EDIT, params, Post.class);
    }

    public Post createPost(ParamRequestModel params) {
        return userApiActions.executeGet(EndPoints.WALL_POST, params, Post.class);
    }

    public void addCommentToPost(ParamRequestModel params) {
        userApiActions.executeGet(EndPoints.CREATE_COMMENT, params);
    }
}

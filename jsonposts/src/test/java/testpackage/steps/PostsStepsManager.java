package testpackage.steps;

import apiutiles.JsonApiGet;
import apiutiles.JsonApiPost;
import framework.enums.MinMaxEnum;
import framework.enums.TypesOfSort;
import framework.utils.Log;
import framework.utils.PropertiesReader;
import models.postsmodels.PostAnswerWithPostRequest;
import models.postsmodels.PostModel;
import org.testng.asserts.SoftAssert;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class PostsStepsManager {
    private final String BODY_URL_TO_GET_POST99 = PropertiesReader.getValue("BODY_URL_TO_GET_POSt99");
    private final String BODY_URL_TO_GET_POST150 = PropertiesReader.getValue("BODY_URL_TO_GET_POSt150");
    private final String BODY_URL_TO_GET_POSTS = PropertiesReader.getValue("BODY_URL_TO_GET_POSTS");
    private final String BODY_URL_TO_POST_POSTS = PropertiesReader.getValue("BODY_URL_TO_POST_POSTS");

    public void post99step() {
        JsonApiGet getPost99 = new JsonApiGet(BODY_URL_TO_GET_POST99);
        PostModel post99Model = getPost99.getModelByMapping(PostModel.class);
        SoftAssert softAssertStepTwo = new SoftAssert();
        softAssertStepTwo.assertEquals(HttpURLConnection.HTTP_OK, getPost99.getResponseCode(), "response code is not 200");
        softAssertStepTwo.assertEquals(10, post99Model.getUserId(), "userId are not match");
        softAssertStepTwo.assertEquals(99, post99Model.getId(), "Id are not match");
        softAssertStepTwo.assertNotNull(post99Model.getBody(), "Body is empty");
        softAssertStepTwo.assertNotNull(post99Model.getTitle(), "Title is empty");
        softAssertStepTwo.assertAll();
    }

    public void postsPostRequest(Map<String, String> params) {
        JsonApiPost jsonApiPost = new JsonApiPost(BODY_URL_TO_POST_POSTS);
        PostAnswerWithPostRequest modelByMapping = jsonApiPost.getModelByMappingPostParams(PostAnswerWithPostRequest.class, params);
        SoftAssert softAssertStepFour = new SoftAssert();
        softAssertStepFour.assertEquals(modelByMapping.getTitle(), params.get("title"), "Titles are not match");
        softAssertStepFour.assertEquals(modelByMapping.getBody(), params.get("body"), "Body are not match");
        softAssertStepFour.assertEquals(modelByMapping.getUserId(), params.get("userId"), "UserId are not match");
        softAssertStepFour.assertNotNull(modelByMapping.getId(),  "Id is not expected");
    }

    public void post150Step() {
        JsonApiGet getPost150 = new JsonApiGet(BODY_URL_TO_GET_POST150);
        SoftAssert softAssertStepThree = new SoftAssert();
        softAssertStepThree.assertEquals(HttpURLConnection.HTTP_NOT_FOUND, getPost150.getResponseCode(),
                 "response code is not 200");
        softAssertStepThree.assertTrue(getPost150.isResponseEmpty(), "File is not empty");
        softAssertStepThree.assertAll();
    }

    public void allPostsStep() {
        JsonApiGet getAllPosts = new JsonApiGet(BODY_URL_TO_GET_POSTS);
        PostModel[] modelByMapping = getAllPosts.getModelByMapping(PostModel[].class);
        SoftAssert softAssertStepOne = new SoftAssert();
        softAssertStepOne.assertTrue(isArrayOfPostsSortedById(TypesOfSort.ASCENDING, modelByMapping),
                "posts are not sorted by id");
        softAssertStepOne.assertEquals(HttpURLConnection.HTTP_OK, getAllPosts.getResponseCode(),
                "response code is not 200");
        softAssertStepOne.assertAll();
    }

    private boolean isArrayOfPostsSortedById(TypesOfSort sortType, PostModel[] posts) {
        ArrayList<Integer> trueIdArray = new ArrayList<>();
        ArrayList<Integer> realIdArray = new ArrayList<>();
        final List<PostModel> postModels = Arrays.asList(posts);
        switch (sortType) {
            case ASCENDING:
                postModels.forEach(post -> realIdArray.add(post.getId()));
                realIdArray.stream().sorted(Comparator.reverseOrder()).forEach(trueIdArray::add);
                return Arrays.equals(realIdArray.toArray(), trueIdArray.toArray());
            case DESCENDING:
                postModels.forEach(post -> realIdArray.add(post.getId()));
                realIdArray.stream().sorted().forEach(trueIdArray::add);
                return Arrays.equals(realIdArray.toArray(), trueIdArray.toArray());
            default:
                EnumConstantNotPresentException enumConstantNotPresentException
                        = new EnumConstantNotPresentException(MinMaxEnum.class, sortType.toString());
                Log.error(Arrays.toString(enumConstantNotPresentException.getStackTrace()));
                throw enumConstantNotPresentException;
        }
    }
}
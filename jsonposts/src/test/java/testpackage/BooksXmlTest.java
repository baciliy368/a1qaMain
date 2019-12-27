package testpackage;

import framework.utils.Log;
import org.testng.annotations.Test;
import testpackage.steps.PostsStepsManager;
import testpackage.steps.UserStepsManager;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.HashMap;
import java.util.Map;

public class BooksXmlTest extends BaseTest {
    private static final int NUMBER_OF_RANDOM_SYMBOLS_IN_STRING = 10;
    private static final String TITLE = RandomStringUtils.randomAlphabetic(NUMBER_OF_RANDOM_SYMBOLS_IN_STRING);
    private static final String BODY = RandomStringUtils.randomAlphabetic(NUMBER_OF_RANDOM_SYMBOLS_IN_STRING);
    public static final String ID = "1";

    @Test
    public void testXmlBooks() {
        Log.info("STEP 1: request for all posts");
        PostsStepsManager postsStepsManager = new PostsStepsManager();
        postsStepsManager.allPostsStep();

        Log.info("STEP 2: request for getting 99 post");
        postsStepsManager.post99step();

        Log.info("STEP 3: request for getting 150 post");
        postsStepsManager.post150Step();

        Log.info("STEP 4:");
        Map<String, String> params = new HashMap<>();
        params.put("title", TITLE);
        params.put("body", BODY);
        params.put("userId", ID);
        postsStepsManager.postsPostRequest(params);

        Log.info("STEP 5: request for getting all users");
        UserStepsManager userStepsManager = new UserStepsManager();
        userStepsManager.usersStep();

        Log.info("STEP 6: request for getting user with id 5");
        userStepsManager.user5Step();
    }
}
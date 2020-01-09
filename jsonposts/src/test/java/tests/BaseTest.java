package tests;

import org.testng.annotations.BeforeTest;
import framework.utils.Log;

public class BaseTest {

    @BeforeTest
    public void beforeTest() {
        Log.setUpLogger();
    }
}

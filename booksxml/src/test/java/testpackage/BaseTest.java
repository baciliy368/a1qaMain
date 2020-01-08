package testpackage;

import framework.utils.Log;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void beforeTest() {
        Log.setUpLogger();
    }
}

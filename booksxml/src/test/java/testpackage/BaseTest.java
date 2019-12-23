package testpackage;

import framework.utils.LoggerOfTests;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void beforeTest() {
        LoggerOfTests.setUpLogger();
    }
}

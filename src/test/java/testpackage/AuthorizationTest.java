package testpackage;

import aquality.selenium.browser.BrowserManager;
import dataprovider.DataProviderClass;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pajeobject.AuthorizationResultPage;

public class AuthorizationTest extends BaseTest {

    @Test(dataProvider = "DataForAuthorization", dataProviderClass = DataProviderClass.class)
    public void basicAuthorization(User user) {
        logger.info("Step 1: open login Page");
        BrowserManager.getBrowser().goTo(String.format("https://%s:%s@httpbin.org/basic-auth/user/passwd", user.getLogin(), user.getPassword()));
        AuthorizationResultPage authorizationResultPage = new AuthorizationResultPage();
        logger.info("check authorization page results");
        Assert.assertEquals(authorizationResultPage.getValueOfAuthorization("user"), user.getLogin(), "It`s not this user");
    }
}

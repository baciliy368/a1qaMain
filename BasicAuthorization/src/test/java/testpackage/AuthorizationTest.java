package testpackage;

import aquality.selenium.browser.BrowserManager;
import dataprovider.DataProviderClass;
import framework.utils.PropertiesReader;
import models.User;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pajeobject.AuthorizationResultPage;

public class AuthorizationTest extends BaseTest {

    @Test(dataProvider = "DataForAuthorization", dataProviderClass = DataProviderClass.class)
    public void basicAuthorization(User user) {
        logger.info("Step 1: open login Page, authorization");
        BrowserManager.getBrowser().goTo(String.format(PropertiesReader.getValue("URL_TO_TEST_DATA"), user.getLogin(), user.getPassword()));
        logger.info("check authorization page results");
        AuthorizationResultPage authorizationResultPage = new AuthorizationResultPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(authorizationResultPage.getValueOfAuthorization("user"), user.getLogin());
        softAssert.assertTrue((Boolean) authorizationResultPage.getValueOfAuthorization("authenticated"));
        softAssert.assertAll("It`s not this user");
    }
}

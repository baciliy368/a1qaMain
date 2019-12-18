package dataprovider;

import framework.utils.JsonParser;
import models.User;
import org.testng.annotations.DataProvider;

public class DataProviderClass {
    @DataProvider(name = "DataForAuthorization")
    public static Object[] dataProviderNumbersPos() {
        return new User[]{new User((JsonParser.getDataFromUsersFile("login")),
                JsonParser.getDataFromUsersFile("password"))};
    }
}

package dataprovider;

import framework.utils.XmlReader;
import org.testng.annotations.DataProvider;

public class DataProviderClass {
    @DataProvider(name = "DataForAuthorization")
    public static Object[] dataProviderNumbersPos() {
        return XmlReader.getUsersData();
    }
}

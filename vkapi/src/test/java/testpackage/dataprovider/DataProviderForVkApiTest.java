package testpackage.dataprovider;

import framework.utils.ModelGenerator;
import framework.utils.PropertiesReader;
import models.test.DataModel;
import models.test.DataProviderTest5Model;
import org.testng.annotations.DataProvider;
import java.io.File;

public class DataProviderForVkApiTest {

    @DataProvider(name = "UserAndPictureDataProvider")
    public static Object[][] dataProviderNumbersPos() {
        final File file = new File(PropertiesReader.getValue("DATA_FILE_FOR_DATA_PROVIDER_TEST_5"));
        final DataModel[] dataList = ModelGenerator.getModelByMappingXml(file, DataProviderTest5Model.class).getData();
        Object[][] result = new Object[dataList.length][2];
        for (int index = 0; index < dataList.length; index++) {
            DataModel dataModel = dataList[index];
            result[index][0] = dataModel.getUserModel();
            result[index][1] = dataModel.getPicFile();
        }
        return result;
    }
}

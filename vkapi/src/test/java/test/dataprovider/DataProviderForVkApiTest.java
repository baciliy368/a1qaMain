package test.dataprovider;

import framework.utils.ModelGenerator;
import framework.utils.PropertiesReader;
import models.test.UserWithFileModel;
import models.test.XmlTestRootModel;
import org.testng.annotations.DataProvider;
import java.io.File;

public class DataProviderForVkApiTest {

    @DataProvider(name = "UserAndPictureDataProvider")
    public static Object[][] dataProviderNumbersPos() {
        final File file = new File(PropertiesReader.getValue("DATA_FILE_FOR_DATA_PROVIDER_TEST_5"));
        final UserWithFileModel[] dataList = ModelGenerator.getModelByMappingXml(file, XmlTestRootModel.class).getData();
        Object[][] result = new Object[dataList.length][2];
        for (int index = 0; index < dataList.length; index++) {
            UserWithFileModel userWithFileModel = dataList[index];
            result[index][0] = userWithFileModel.getUserModel();
            result[index][1] = userWithFileModel.getPicFile();
        }
        return result;
    }
}

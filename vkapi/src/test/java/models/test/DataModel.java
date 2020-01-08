package models.test;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import framework.utils.ModelGenerator;
import java.io.File;

public class DataModel {
    @JacksonXmlProperty(localName = "user")
    String userPath;
    @JacksonXmlProperty(localName = "pic")
    String picPath;

    public UserModel getUserModel() {
        return ModelGenerator.getModelByMapping(new File(userPath), UserModel.class);
    }

    public File getPicFile() {
        return new File(picPath);
    }
}

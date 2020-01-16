package models.test;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "dataList")
public class XmlTestRootModel {
    @JacksonXmlElementWrapper(useWrapping = false)
    private UserWithFileModel[] data;

    public UserWithFileModel[] getData() {
        return data;
    }
}

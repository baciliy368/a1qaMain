package models.test;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "dataList")
public class DataProviderTest5Model {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "data")
    private DataModel[] data;

    public DataModel[] getData() {
        return data;
    }
}

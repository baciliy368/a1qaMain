package framework.utils;

import exceptions.NoPropertiesFileException;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.StringReader;

public class XmlReader {

    public static Element getRootElement(String xml) {
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            return saxBuilder.build(new StringReader(xml)).getRootElement();
        } catch (Exception e) {
            LoggerOfTests.logger.error(e.getMessage());
            throw new NoPropertiesFileException();
        }
    }
}

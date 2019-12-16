package framework.utils;

import models.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlReader {
    public static User[] getUsersData() {
        User[] arrUsers = {};
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(PropertiesReader.getValue("PATH_TO_FILE_WITH_USER_DATA")));
            NodeList users = doc.getDocumentElement().getElementsByTagName("user");
            arrUsers = new User[users.getLength()];
            for (int item = 0; item < users.getLength(); item++) {
                Element el = (Element) users.item(item);
                arrUsers[item] = new User(el.getElementsByTagName("login").item(0).getTextContent(),
                        el.getElementsByTagName("password").item(0).getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrUsers;
    }
}

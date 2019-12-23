package apiutiles;

import framework.baseelement.BasicApi;
import models.Book;
import org.jdom2.Element;
import framework.utils.PropertiesReader;
import framework.utils.XMLReader;
import java.util.ArrayList;
import java.util.List;

public class BooksApi extends BasicApi {
    private static final String url = PropertiesReader.getValue("URL_TO_GET_BOOKS");

    public BooksApi() {
        super(url);
    }

    public ArrayList<Book> getBooksList() {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        Element rootElement = XMLReader.getRootElement(getTextOfResponse());
        List<Element> children = rootElement.getChildren();
        for (Element child : children) {
            bookArrayList.add(new Book(child));
        }
        return bookArrayList;
    }
}


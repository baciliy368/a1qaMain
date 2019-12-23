package models;

import org.jdom2.Element;

public class Book {
    private String id;
    private String description;
    private double price;
    private String name;

    public Book(Element element) {
        id = element.getAttributeValue("id");
        description = element.getChildText("description");
        price = Double.parseDouble(element.getChildText("price"));
        name = element.getChildText("title");
    }

    public int getIntOfId() {
        return Integer.parseInt(id.replaceAll("[^\\d]", ""));
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}

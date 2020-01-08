package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookModel {
    @JacksonXmlProperty(isAttribute = true)
    private String id;
    private String description;
    private double price;
    @JacksonXmlProperty(localName = "title")
    private String name;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

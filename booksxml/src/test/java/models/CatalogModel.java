package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import framework.MinMaxValue;
import framework.utils.Log;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import static framework.MinMaxValue.MAX;
import static framework.MinMaxValue.MIN;

@JacksonXmlRootElement(localName = "catalog")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatalogModel {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "book")
    private ArrayList<BookModel> books;

    public boolean areBooksSorted(MinMaxValue sortedBy) {
        Log.info("check are Books sorted By id");
        ArrayList<String> realIdArray = new ArrayList<>();
        ArrayList<String> trueIdArray = new ArrayList<>();
        switch (sortedBy) {
            case MIN:
                books.forEach(book -> realIdArray.add(book.getId()));
                realIdArray.stream().sorted(Comparator.reverseOrder()).forEach(trueIdArray::add);
                break;
            case MAX:
                books.forEach(book -> realIdArray.add(book.getId()));
                realIdArray.stream().sorted().forEach(trueIdArray::add);
                break;
            default:
        }

        return Arrays.equals(trueIdArray.toArray(), realIdArray.toArray());
    }

    public BookModel getBookWithOptionalSearch(MinMaxValue option) {
        Log.info("search BOOK with Top price");
        switch (option) {
            case MAX:
                return books.stream()
                        .max(Comparator.comparing(BookModel::getPrice))
                        .orElseThrow(() -> new NoSuchElementException("The are No Book model on request" + MAX));
            case MIN:
                return books.stream()
                        .min(Comparator.comparing(BookModel::getPrice))
                        .orElseThrow(() -> new NoSuchElementException("The are No Book model on request" + MIN));
            default:
                final NoSuchElementException noSuchElementException = new NoSuchElementException("Unknown selection condition");
                Log.error(Arrays.toString(noSuchElementException.getStackTrace()));
                throw noSuchElementException;
        }
    }
}

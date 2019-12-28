package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import framework.enums.MinMaxValue;
import framework.enums.TypeOfSort;
import framework.utils.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import static framework.enums.MinMaxValue.MAX;
import static framework.enums.MinMaxValue.MIN;

@JacksonXmlRootElement(localName = "catalog")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatalogModel {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "book")
    private ArrayList<BookModel> books;

    public boolean areBooksSorted(TypeOfSort sortedBy) {
        Log.info("check are Books sorted By id");
        ArrayList<String> realIdArray = new ArrayList<>();
        ArrayList<String> trueIdArray = new ArrayList<>();
        switch (sortedBy) {
            case ASCENDING:
                books.forEach(book -> realIdArray.add(book.getId()));
                realIdArray.stream().sorted(Comparator.reverseOrder()).forEach(trueIdArray::add);
                return Arrays.equals(trueIdArray.toArray(), realIdArray.toArray());
            case DESCENDING:
                books.forEach(book -> realIdArray.add(book.getId()));
                realIdArray.stream().sorted().forEach(trueIdArray::add);
                return Arrays.equals(trueIdArray.toArray(), realIdArray.toArray());
            default:
                EnumConstantNotPresentException enumConstantNotPresentException
                        = new EnumConstantNotPresentException(MinMaxValue.class, sortedBy.toString());
                Log.error(Arrays.toString(enumConstantNotPresentException.getStackTrace()));
                throw enumConstantNotPresentException;
        }
    }

    public BookModel getBookWithOptionalSearch(MinMaxValue option) {
        Log.info("search BOOK with Top price");
        switch (option) {
            case MAX:
                return books.stream()
                        .max(Comparator.comparing(BookModel::getPrice))
                        .orElseThrow(() -> new NoSuchElementException("The are No Element model on request" + MAX));
            case MIN:
                return books.stream()
                        .min(Comparator.comparing(BookModel::getPrice))
                        .orElseThrow(() -> new NoSuchElementException("The are No Element model on request" + MIN));
            default:
                EnumConstantNotPresentException enumConstantNotPresentException
                        = new EnumConstantNotPresentException(MinMaxValue.class, option.toString());
                Log.error(Arrays.toString(enumConstantNotPresentException.getStackTrace()));
                throw enumConstantNotPresentException;
        }
    }
}

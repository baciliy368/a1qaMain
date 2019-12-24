package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import exceptions.NoCaseForThisEnumException;
import framework.utils.Log;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

@JacksonXmlRootElement(localName = "catalog")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatalogModel {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "book")
    private ArrayList<BookModel> books;

    public enum OptionsOfCatalogOperations {
        MAX,
        MIN
    }

    public boolean areBooksSorted(OptionsOfCatalogOperations sortedBy) {
        Log.LOG.info("check are Books sorted By id");
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
                final NoCaseForThisEnumException noCaseForThisEnumException = new NoCaseForThisEnumException();
                Log.LOG.error(noCaseForThisEnumException);
                throw noCaseForThisEnumException;
        }
        for (int index = 0; index < realIdArray.size(); index++) {
            if (!realIdArray.get(index).equals(trueIdArray.get(index))) {
                return false;
            }
        }
        return true;
    }

    public BookModel getBookWithOptionalSearch(OptionsOfCatalogOperations option) {
        Log.LOG.info("search BOOK with Top price");
        switch (option) {
            case MAX:
                return books
                        .stream()
                        .max(Comparator.comparing(BookModel::getPrice))
                        .orElseThrow(NoSuchElementException::new);
            case MIN:
                return books
                        .stream()
                        .min(Comparator.comparing(BookModel::getPrice))
                        .orElseThrow(NoSuchElementException::new);
            default:
                final NoSuchElementException noSuchElementException = new NoSuchElementException();
                Log.LOG.error(noSuchElementException.getStackTrace());
                throw noSuchElementException;
        }
    }
}

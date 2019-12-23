package framework.utils;

import models.Book;
import java.util.ArrayList;

public class BooksManager {

    public boolean isBooksSort(ArrayList<Book> booksArrayList) {
        LoggerOfTests.logger.info("check are Books sorted By id");
        for (int index = 1; index < booksArrayList.size(); index++) {
            if (!(booksArrayList.get(index).getIntOfId() >= booksArrayList.get(index - 1).getIntOfId())) {
                return false;
            }
        }
        return true;
    }

    public Book getBookWithTopPrice(ArrayList<Book> booksArrayList) {
        int indexOfTopBook = 0;
        LoggerOfTests.logger.info("search BOOK with Top price");
        for (int index = 1; index < booksArrayList.size(); index++) {
            if (booksArrayList.get(indexOfTopBook).getPrice() < booksArrayList.get(index).getPrice()) {
                indexOfTopBook = index;
            }
        }
        return booksArrayList.get(indexOfTopBook);
    }

    public Book getBookWithMinimalPrice(ArrayList<Book> booksArrayList) {
        int indexOfTopBook = 0;
        LoggerOfTests.logger.info("search BOOK with Minimal price");
        for (int index = 1; index < booksArrayList.size(); index++) {
            if (booksArrayList.get(indexOfTopBook).getPrice() > booksArrayList.get(index).getPrice()) {
                indexOfTopBook = index;
            }
        }
        return booksArrayList.get(indexOfTopBook);
    }
}

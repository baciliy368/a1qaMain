package testpackage;

import apiutiles.BooksApi;
import framework.utils.LoggerOfTests;
import models.Book;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import framework.utils.BooksManager;
import sun.rmi.runtime.Log;

public class BooksXmlTest extends BaseTest {

    @Test
    public void testXmlBooks() {
        BooksApi booksApi = new BooksApi();
        BooksManager booksManager = new BooksManager();

        LoggerOfTests.logger.info("Step 1: Check status code and xml books, books don't sorted");
        SoftAssert softAssertStepOne = new SoftAssert();
        softAssertStepOne.assertEquals(200, booksApi.getResponseCode());
        softAssertStepOne.assertEquals("application/xml; charset=UTF-8",
                booksApi.getConnection().getHeaderField("Content-Type"));
        softAssertStepOne.assertTrue(booksManager.isBooksSort(booksApi.getBooksList()));
        softAssertStepOne.assertAll("Code is not 200 or it is not .xml");

        LoggerOfTests.logger.info("Step 2: Check name and description of books");
        SoftAssert softAssertStepTwo = new SoftAssert();
        Book topPriceBook = booksManager.getBookWithTopPrice(booksApi.getBooksList());
        Book minimalPriceBook = booksManager.getBookWithMinimalPrice(booksApi.getBooksList());
        softAssertStepTwo.assertFalse(minimalPriceBook.getDescription().equals(topPriceBook.getDescription()));
        softAssertStepTwo.assertFalse(topPriceBook.getName().equals(minimalPriceBook.getName()));
        softAssertStepTwo.assertAll("name or description of books with heist and lowest price is same");
    }
}

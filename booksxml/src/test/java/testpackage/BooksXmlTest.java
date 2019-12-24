package testpackage;

import apiutiles.BooksApi;
import framework.utils.Log;
import models.Book;
import models.Catalog;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BooksXmlTest extends BaseTest {

    @Test
    public void testXmlBooks() {
        BooksApi booksApi = new BooksApi();
        Catalog catalog = booksApi.getModelsFromRoot(Catalog.class);
        Log.LOG.info("Step 1: Check status code and xml books, books don't sorted");
        SoftAssert softAssertStepOne = new SoftAssert();
        softAssertStepOne.assertEquals(200, booksApi.getResponseCode(), "Code is not 200");
        softAssertStepOne.assertEquals("application/xml; charset=UTF-8",
                booksApi.getConnection().getHeaderField("Content-Type"), "it is not .xml file");
        softAssertStepOne.assertTrue(catalog.areBooksSorted(Catalog.OptionsOfCatalogOperations.MAX),
                "books are not sorted by id");
        softAssertStepOne.assertAll();

        Log.LOG.info("Step 2: Check name and description of books");
        SoftAssert softAssertStepTwo = new SoftAssert();
        Book topPriceBook = catalog.getBookWithOptionalSearch(Catalog.OptionsOfCatalogOperations.MAX);
        Book minimalPriceBook = catalog.getBookWithOptionalSearch(Catalog.OptionsOfCatalogOperations.MIN);
        softAssertStepTwo.assertFalse(minimalPriceBook.getDescription().equals(topPriceBook.getDescription()),
                "description of top price and minimal price book match");
        softAssertStepTwo.assertFalse(minimalPriceBook.getName().equals(topPriceBook.getName()),
                "name of top price and minimal price book match");
        softAssertStepTwo.assertAll();
    }
}

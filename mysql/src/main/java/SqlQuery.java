import framework.utiles.DbUtiles.DbOperations;
import framework.utiles.DbUtiles.SqlFileReader;
import framework.utiles.Printer;
import models.mysqlquery.BrowsersNumber;
import models.mysqlquery.DateFromQuery;
import models.mysqlquery.TimeSpendOnTest;
import models.mysqlquery.UniqueTestOnProject;

import java.util.List;

public class SqlQuery {

    public static void main(String[] args) {
        Printer.print("Point 1: Minimal work time of tests");
        List<TimeSpendOnTest> minimalWorkTimeOnBrowser = getTimeSpendOnTests();
        Printer.print(minimalWorkTimeOnBrowser, new String[]{"project", "nameOfTest", "time"});

        Printer.print("Point 2: Number of unique test on the project");
        List<UniqueTestOnProject> UniqueTestsOnProject = getUniqueTestsFromQuery();
        Printer.print(UniqueTestsOnProject, new String[]{"project", "number"} );

        Printer.print("Point 3: All tests for every project after the date");
        List<DateFromQuery> testDurationModelsDate = getListOfDateFromQuery("2015-11-07");
        Printer.print(testDurationModelsDate,  new String[]{"project", "nameOfTest", "date"});

        Printer.print("Point 4: Number of test in browser");
        List<BrowsersNumber> numberTestsInBrowser = getNumberOfTestsInBrowsers(new String[]{"chrome", "firefox"});
        Printer.print(numberTestsInBrowser, new String[]{"number"});
    }

    private static List<UniqueTestOnProject> getUniqueTestsFromQuery() {
        String query = new SqlFileReader("UNIQUE_TESTS_ON_PROJECT").toString();
        return DbOperations.executeQuery(query, UniqueTestOnProject.class);
    }

    private static List<TimeSpendOnTest> getTimeSpendOnTests() {
        String query = new SqlFileReader("TIME_SPEND_ON_TEST_SCRIPT").toString();
        return DbOperations.executeQuery(query, TimeSpendOnTest.class);
    }

    private static List<BrowsersNumber> getNumberOfTestsInBrowsers(String[] browsers) {
        String pattern = new SqlFileReader("BROWSER_SCRIPT").toString();
        String query = String.format(pattern, browsers[0]);
        if (browsers.length > 1) {
            for (int browserIndex = 1; browserIndex < browsers.length; browserIndex++) {
                query = String.format("%s UNION %s", query, String.format(pattern, browsers[browserIndex]));
            }
        }
        return DbOperations.executeQuery(query, BrowsersNumber.class);
    }

    private static List<DateFromQuery> getListOfDateFromQuery(String date) {
        String query = new SqlFileReader("DATE_SCRIPT").toString();
        return DbOperations.executeQuery(String.format(query, date), DateFromQuery.class);
    }
}

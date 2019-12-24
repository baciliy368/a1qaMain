import framework.utiles.dbutiles.DbOperations;
import framework.utiles.dbutiles.SqlFileReader;
import framework.utiles.Printer;
import models.mysqlquery.BrowsersNumberModel;
import models.mysqlquery.DateFromModel;
import models.mysqlquery.TimeSpendOnTestModel;
import models.mysqlquery.UniqueTestOnProjectModel;

import java.util.List;

public class SqlQuery {

    public static void main(String[] args) {
        Printer.print("Point 1: Minimal work time of tests");
        List<TimeSpendOnTestModel> minimalWorkTimeOnBrowser = getTimeSpendOnTests();
        Printer.print(minimalWorkTimeOnBrowser, new String[]{"project", "nameOfTest", "time"});

        Printer.print("Point 2: Number of unique test on the project");
        List<UniqueTestOnProjectModel> uniqueTestsOnProject = getUniqueTestsFromQuery();
        Printer.print(uniqueTestsOnProject, new String[]{"project", "number"});

        Printer.print("Point 3: All tests for every project after the date");
        List<DateFromModel> testDurationModelsDate = getListOfDateFromQuery("2015-11-07");
        Printer.print(testDurationModelsDate,  new String[]{"project", "nameOfTest", "date"});

        Printer.print("Point 4: Number of test in browser");
        List<BrowsersNumberModel> numberTestsInBrowser = getNumberOfTestsInBrowsers(new String[]{"chrome", "firefox"});
        Printer.print(numberTestsInBrowser, new String[]{"number"});
    }

    private static List<UniqueTestOnProjectModel> getUniqueTestsFromQuery() {
        String query = new SqlFileReader("UNIQUE_TESTS_ON_PROJECT").toString();
        return DbOperations.executeQuery(query, UniqueTestOnProjectModel.class);
    }

    private static List<TimeSpendOnTestModel> getTimeSpendOnTests() {
        String query = new SqlFileReader("TIME_SPEND_ON_TEST_SCRIPT").toString();
        return DbOperations.executeQuery(query, TimeSpendOnTestModel.class);
    }

    private static List<BrowsersNumberModel> getNumberOfTestsInBrowsers(String[] browsers) {
        String pattern = new SqlFileReader("BROWSER_SCRIPT").toString();
        String query = String.format(pattern, browsers[0]);
        if (browsers.length > 1) {
            for (int browserIndex = 1; browserIndex < browsers.length; browserIndex++) {
                query = String.format("%s UNION %s", query, String.format(pattern, browsers[browserIndex]));
            }
        }
        return DbOperations.executeQuery(query, BrowsersNumberModel.class);
    }

    private static List<DateFromModel> getListOfDateFromQuery(String date) {
        String query = new SqlFileReader("DATE_SCRIPT").toString();
        return DbOperations.executeQuery(String.format(query, date), DateFromModel.class);
    }
}

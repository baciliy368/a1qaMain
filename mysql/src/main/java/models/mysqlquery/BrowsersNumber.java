package models.mysqlquery;

public class BrowsersNumber {
    private String numberOfBrowsers;

    @Override
    public String toString() {
        return String.format("%s", numberOfBrowsers);
    }
}

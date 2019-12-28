package models.mysqlquery;

public class BrowsersNumberModel {
    private String numberOfBrowsers;

    @Override
    public String toString() {
        return String.format("%s", numberOfBrowsers);
    }
}

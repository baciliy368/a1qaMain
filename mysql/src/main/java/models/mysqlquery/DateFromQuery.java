package models.mysqlquery;

public class DateFromQuery {
    private String project;
    private String testName;
    private String endTime;

    @Override
    public String toString() {
        return String.format("%s|%s|%s", project, testName, endTime);
    }
}

package models.mysqlquery;

public class UniqueTestOnProjectModel {
    private String project;
    private String numberOfTests;

    @Override
    public String toString() {
        return String.format("%s|%s", project, numberOfTests);
    }
}


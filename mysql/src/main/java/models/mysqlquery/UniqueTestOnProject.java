package models.mysqlquery;

public class UniqueTestOnProject {
    private String project;
    private String numberOfTests;

    @Override
    public String toString() {
        return String.format("%s|%s", project, numberOfTests);
    }
}


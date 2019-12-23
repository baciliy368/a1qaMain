package models.mysqlquery;

public class TimeSpendOnTest {
    private String project;
    private String nameOfTest;
    private String timeDifference;

    @Override
    public String toString() {
        return String.format("%s|%s|%s", project, nameOfTest, timeDifference);
    }
}

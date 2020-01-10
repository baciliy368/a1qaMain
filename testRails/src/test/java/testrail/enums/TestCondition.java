package testrail.enums;

public enum TestCondition {
    PASSED("1"),
    BLOCKED("2"),
    RETEST("3"),
    FAILED("4"),
    PASSED_WITH_ISSUES("5");
    private String condition;

    TestCondition(String action) {
        this.condition = action;
    }

    @Override
    public String toString() {
        return condition;
    }
}

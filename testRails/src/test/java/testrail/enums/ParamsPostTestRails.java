package testrail.enums;

public enum ParamsPostTestRails {
    STATUS_OF_TEST("status_id"),
    COMMENT("comment");
    private String condition;

    ParamsPostTestRails(String action) {
        this.condition = action;
    }

    @Override
    public String toString() {
        return condition;
    }
}

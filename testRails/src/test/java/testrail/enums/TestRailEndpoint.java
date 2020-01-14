package testrail.enums;

public enum TestRailEndpoint {
    ADD_ATTACHMENT_TO_RESULT("add_attachment_to_result"),
    ADD_RESULT_TO_CASE("add_result_for_case");

    private String endPoint;

    TestRailEndpoint(String action) {
        this.endPoint = action;
    }

    @Override
    public String toString() {
        return endPoint;
    }
}

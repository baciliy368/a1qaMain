package testrail.enums;

public enum TestRailEndpoint {
    ADD_SUITE("add_suite"),
    GET_SUITE("get_suite"),
    ADD_SECTION("add_section"),
    GET_SECTION("get_section"),
    ADD_CASE("add_case"),
    GET_CASE("get_case"),
    ADD_RUN("add_run"),
    GET_RUN("get_run"),
    ADD_RESULT_OF_CASE("add_result_for_case"),
    DELETE_RUN("delete_run"),
    DELETE_CASE("delete_case"),
    DELETE_SECTION("delete_section"),
    DELETE_SUITE("delete_suite");

    private String endPoint;

    TestRailEndpoint(String action) {
        this.endPoint = action;
    }

    @Override
    public String toString() {
        return endPoint;
    }
}

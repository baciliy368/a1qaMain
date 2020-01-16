package testrail.api;

import models.TestResultModel;

public class TestRailHelper {
    public TestResultModel getTestResult(int result, String comment) {
        TestResultModel testResult = new TestResultModel();
        testResult.setStatusId(result);
        testResult.setComment(comment);
        return testResult;
    }
}

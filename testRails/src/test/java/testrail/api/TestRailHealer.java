package testrail.api;

import models.TestResultModel;

public class TestRailHealer {
    public TestResultModel getTestResult(int result, String comment) {
        TestResultModel testResult = new TestResultModel();
        testResult.setResult(result);
        testResult.setComment(comment);
        return testResult;
    }
}

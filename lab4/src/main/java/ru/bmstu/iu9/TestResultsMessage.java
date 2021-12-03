package ru.bmstu.iu9;

import java.util.ArrayList;

public class TestResultsMessage {
    private final String packageId;
    private final ArrayList<String> testResults;

    public String getPackageId() {
        return packageId;
    }

    public ArrayList<String> getTestResults() {
        return testResults;
    }

    public TestResultsMessage(String packageId, ArrayList<String> testResults) {
        this.packageId = packageId;
        this.testResults = testResults;
    }
}

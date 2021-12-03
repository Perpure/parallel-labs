package ru.bmstu.iu9;

import java.util.ArrayList;

public class TestResultsMessage {
    private final int packageId;
    private final ArrayList<String> testResults;

    public int getPackageId() {
        return packageId;
    }

    public ArrayList<String> getTestResults() {
        return testResults;
    }

    public TestResultsMessage(int packageId, ArrayList<String> testResults) {
        this.packageId = packageId;
        this.testResults = testResults;
    }
}

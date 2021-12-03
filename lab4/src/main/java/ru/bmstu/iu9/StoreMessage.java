package ru.bmstu.iu9;

public class StoreMessage {
    private final int packageId;
    private final String testResult;


    public StoreMessage(int packageId, String testResult) {
        this.packageId = packageId;
        this.testResult = testResult;
    }

    public int getPackageId() {
        return packageId;
    }

    public String getTestResult() {
        return testResult;
    }
}

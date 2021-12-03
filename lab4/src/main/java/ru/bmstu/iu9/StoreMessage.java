package ru.bmstu.iu9;

public class StoreMessage {
    private final String packageId;
    private final String testResult;


    public StoreMessage(String packageId, String testResult) {
        this.packageId = packageId;
        this.testResult = testResult;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getTestResult() {
        return testResult;
    }
}

package ru.bmstu.iu9;

public class StoreMessage {
    private int packageId;
    private String testResult;


    public StoreMessage(int packageId, String testResult) {
        this.packageId = packageId;
        this.testResult = testResult;
    }
}

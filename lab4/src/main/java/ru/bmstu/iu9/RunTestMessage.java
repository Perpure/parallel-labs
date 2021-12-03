package ru.bmstu.iu9;

import java.util.ArrayList;

public class RunTestMessage {
    private final String packageId;
    private final String jsScript;
    private final String functionName;
    private final String testName;
    private final String expectedResult;

    public String getPackageId() {
        return packageId;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getTestName() {
        return testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public ArrayList<Object> getParams() {
        return params;
    }

    public RunTestMessage(String packageId, String jsScript, String functionName, String testName, String expectedResult, ArrayList<Object> params) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    private final ArrayList<Object> params;

}

package ru.bmstu.iu9;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class JsonRequest {
    @JsonProperty("packageId")
    private final String packageId;

    @JsonProperty("jsScript")
    private final String jsScript;

    @JsonProperty("functionName")
    private final String functionName;

    @JsonProperty("tests")
    private final ArrayList<JsonTest> tests;


    public JsonRequest(@JsonProperty("packageId") String packageId,
                       @JsonProperty("jsScript")String jsScript,
                       @JsonProperty("functionName") String functionName,
                       @JsonProperty("tests") ArrayList<JsonTest> tests) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    @JsonProperty("packageId")
    public String getPackageId() {
        return packageId;
    }

    @JsonProperty("jsScript")
    public String getJsScript() {
        return jsScript;
    }

    @JsonProperty("functionName")
    public String getFunctionName() {
        return functionName;
    }

    @JsonProperty("tests")
    public ArrayList<JsonTest> getTests() {
        return tests;
    }
}

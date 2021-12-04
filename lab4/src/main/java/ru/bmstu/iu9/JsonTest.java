package ru.bmstu.iu9;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class JsonTest {
    @JsonProperty("testName")
    private final String testName;

    @JsonProperty("expectedResult")
    private final String expectedResult;

    @JsonProperty("params")
    private final ArrayList<Object> params;

    @JsonProperty("testName")
    public String getTestName() {
        return testName;
    }

    @JsonProperty("expectedResult")
    public String getExpectedResult() {
        return expectedResult;
    }

    @JsonProperty("params")
    public ArrayList<Object> getParams() {
        return params;
    }



    @JsonCreator
    public JsonTest(@JsonProperty("testName") String testName,
                    @JsonProperty("expectedResult") String expectedResult,
                    @JsonProperty("params") ArrayList<Object> params) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }
}

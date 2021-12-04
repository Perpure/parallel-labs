package ru.bmstu.iu9;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonTest {
    @JsonProperty("testName")
    private final String testName;

    @JsonProperty
    private final String expectedResult;

    @JsonProperty
    private final String params;

}

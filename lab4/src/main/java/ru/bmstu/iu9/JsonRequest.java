package ru.bmstu.iu9;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRequest {
    @JsonProperty
    private final String packageId;

    @JsonProperty
    private final String jsScript;

    @JsonProperty
    private final String functionName;

    @JsonProperty
    private final String test;

}

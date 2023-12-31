package com.measure.execute.time.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LogInfo {
    @JsonProperty
    private String className;
    @JsonProperty
    private String methodName;
    private double executeTime;
    @JsonProperty
    private Object payload;

    public LogInfo(String className, String methodName, double executeTime, Object payload) {
        this.className = className;
        this.methodName = methodName;
        this.executeTime = executeTime;
        this.payload = payload;
    }
}

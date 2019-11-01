package ru.labs.jstester;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@JsonProperty(PropertyNamingStrategy.SNAKE_CASE.class)
public class TestMetaInfo {
    private String packageID;
    private String jsCode;
    private String functionName;

    public TestMetaInfo(String packageID, String jsCode, String functionName) {
        this.packageID = packageID;
        this.jsCode = jsCode;
        this.functionName = functionName;
    }

    public String getPackageID() {
        return packageID;
    }

    public String getJsCode() {
        return jsCode;
    }

    public String getFunctionName() {
        return functionName;
    }
}

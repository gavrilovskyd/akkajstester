package ru.labs.jstester;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestMetaInfo {
    @JsonProperty("")
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

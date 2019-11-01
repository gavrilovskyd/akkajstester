package ru.labs.jstester;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestMetaInfo {
    @JsonProperty("package_id")
    private String packageID;
    @JsonProperty("js_code")
    private String jsCode;
    @JsonProperty("function_name")
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

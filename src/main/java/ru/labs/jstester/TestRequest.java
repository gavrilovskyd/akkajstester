package ru.labs.jstester;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

public class TestRequest {
    //private TestMetaInfo meta;
    private String packageID;
    private String jsCode;
    private String functionName;
    private Test[] tests;

    public TestRequest(String packageID, String jsCode, String functionName, Test[] tests) {
        //this.meta = new TestMetaInfo(packageID, jsCode, functionName);
        this.packageID = packageID;
        this.jsCode = jsCode;
        this.functionName = functionName;
        this.tests = tests;
    }

    //public TestMetaInfo getMeta() {
    //    return meta;
    //}

    public String getPackageID() {
        return packageID;
    }

    public String getJsCode() {
        return jsCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public Test[] getTests() {
        return tests;
    }
}

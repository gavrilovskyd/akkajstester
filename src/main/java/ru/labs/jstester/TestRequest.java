package ru.labs.jstester;

public class TestRequest {
    private String packageID;
    private String jsCode;
    private String functionName;
    private Test[] tests;

    public TestRequest(String packageID, String jsCode, String functionName, Test[] tests) {
        this.packageID = packageID;
        this.jsCode = jsCode;
        this.functionName = functionName;
        this.tests = tests;
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

    public Test[] getTests() {
        return tests;
    }
}

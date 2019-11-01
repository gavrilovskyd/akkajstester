package ru.labs.jstester;

public class TestTask {
    private TestMetaInfo meta;
    private Test test;

    public TestTask(String packageID, String jsCode, String functionName, Test test) {
        this.packageID = packageID;
        this.jsCode = jsCode;
        this.functionName = functionName;
        this.test = test;
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

    public Test getTest() {
        return test;
    }
}

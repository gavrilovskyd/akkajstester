package ru.labs.jstester;

public class TestRequest {
    private TestMetaInfo meta;
    private Test[] tests;

    public TestRequest(String packageID, String jsCode, String functionName, Test[] tests) {
        this.packageID = packageID;
        this.jsCode = jsCode;
        this.functionName = functionName;
        this.tests = tests;
    }

    public Test[] getTests() {
        return tests;
    }
}

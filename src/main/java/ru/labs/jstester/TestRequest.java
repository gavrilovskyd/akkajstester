package ru.labs.jstester;

public class TestRequest {
    private TestMetaInfo meta;
    private Test[] tests;

    public TestRequest(String packageID, String jsCode, String functionName, Test[] tests) {
        this.meta = new TestMetaInfo(packageID, jsCode, functionName);
        this.tests = tests;
    }

    public Test[] getTests() {
        return tests;
    }
}

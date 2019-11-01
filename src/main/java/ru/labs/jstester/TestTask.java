package ru.labs.jstester;

public class TestTask {
    private String packageID;
    private String jsCode;
    private String functionName;
    private Test[] tests;

    public TestTask(String packageID, String jsCode, String functionName, Test[] tests) {
        this.packageID = packageID;
        this.jsCode = jsCode;
        this.functionName = functionName;
        this.tests = tests;
    }
}

package ru.labs.jstester;

public class Test {
    private int innerID;
    private String testName;
    private String expectedResult;
    private Object[] params;

    public Test(String name, String expectedResult, Object[] params) {
        this.testName = name;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public String getTestName() {
        return testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public Object[] getParams() {
        return params;
    }
}

package ru.labs.jstester;

public class Test {
    private String testName;
    private String expectedResult;
    private Object[] params;

    public Test(String name, String expected, Object[] params) {
        this.testName = name;
        this.expectedResult = expected;
        this.params = params;
    }
}

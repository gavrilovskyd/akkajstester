package ru.labs.jstester;

public class Test {
    private int innerID;
    private String packageID;
    private String testName;
    private String expectedResult;
    private Object[] params;

    public Test(int innerID, String packageID, String name, String expectedResult, Object[] params) {
        this.innerID = innerID;
        this.packageID = packageID;
        this.testName = name;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public int getInnerID() {
        return innerID;
    }

    public String getPackageID() {
        return packageID;
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

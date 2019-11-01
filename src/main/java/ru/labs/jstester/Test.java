package ru.labs.jstester;

public class Test {
    private String packageID;
    private String testName;
    private String expectedResult;
    private Object[] params;

    public Test(String packageID, String name, String expectedResult, Object[] params) {
        this.packageID = packageID;
        this.testName = name;
        this.expectedResult = expectedResult;
        this.params = params;
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

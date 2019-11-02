package ru.labs.jstester.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Test {
    @JsonIgnore
    private String packageID;
    private String testName;
    private String expectedResult;
    private Object[] params;

    public Test() {
        this.packageID = "";
        this.testName = "";
        this.expectedResult = "";
        this.params = new Object[]{};
    }

    public Test(String name, String expectedResult, Object[] params) {
        this.packageID = "";
        this.testName = name;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
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

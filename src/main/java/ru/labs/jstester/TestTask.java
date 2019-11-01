package ru.labs.jstester;

public class TestTask {
    private TestMetaInfo meta;
    private Test test;

    public TestTask(TestMetaInfo meta, Test test) {
        this.meta = meta;
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

package ru.labs.jstester;

public class TestMetaInfo {
    private String packageID;
    private String jsCode;
    private String functionName;

    public TestMetaInfo(String packageID, String jsCode, String functionName) {
        this.packageID = packageID;
        this.jsCode = jsCode;
        this.functionName = functionName;
    }
}

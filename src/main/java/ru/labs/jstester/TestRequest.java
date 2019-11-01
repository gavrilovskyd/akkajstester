package ru.labs.jstester;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TestRequest extends TestMetaInfo {
    private Test[] tests;

    public TestRequest() {
        this.meta = new TestMetaInfo();
        this.tests = new Test[]{};
    }

    public TestRequest(String packageID, String jsCode, String functionName, Test[] tests) {
        this.meta = new TestMetaInfo(packageID, jsCode, functionName);
        this.tests = tests;
    }

    public TestMetaInfo getMeta() {
        return meta;
    }

    public Test[] getTests() {
        return tests;
    }
}

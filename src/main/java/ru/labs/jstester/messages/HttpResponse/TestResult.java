package ru.labs.jstester.messages;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TestResult {
    public static final String OK_STATUS = "OK";
    public static final String WRONG_ANSWER_STATUS = "WA";
    public static final String RUNTIME_ERROR_STATUS = "RE";

    private Test test;
    private String actualResult;
    private String status;

    public TestResult(Test test, String actualResult, String status) {
        this.test = test;
        this.actualResult = actualResult;
        this.status = status;
    }

    public Test getTest() {
        return test;
    }

    public String getActualResult() {
        return actualResult;
    }

    public String getStatus() {
        return status;
    }
}

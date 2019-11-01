package ru.labs.jstester;

public class TestResult {
    private static final String OK_STATUS = "OK";
    private static final String WRONG_ANSWER_STATUS = "WA";
    private static final String TIME_LIMIT_STATUS = "TL";

    private Test test;
    private String actualResult;
    private String status;

    public TestResult(Test test, String actualResult, String status) {
        this.test = test;
        this.actualResult = actualResult;
        this.status = status;
    }
}

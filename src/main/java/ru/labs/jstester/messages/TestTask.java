package ru.labs.jstester.messages;

public class TestTask {
    private TestMetaInfo meta;
    private Test test;

    public TestTask(TestMetaInfo meta, Test test) {
        this.meta = meta;
        this.test = test;
    }

    public TestMetaInfo getMeta() {
        return meta;
    }

    public Test getTest() {
        return test;
    }
}

package ru.labs.jstester;

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

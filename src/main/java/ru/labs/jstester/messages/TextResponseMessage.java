package ru.labs.jstester.messages;

public class TextResponseMessage {
    private String message;

    public TextResponseMessage() {
        this.message = "";
    }

    public TextResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

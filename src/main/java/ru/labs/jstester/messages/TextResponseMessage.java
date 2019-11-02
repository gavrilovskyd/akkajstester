package ru.labs.jstester.messages;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
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

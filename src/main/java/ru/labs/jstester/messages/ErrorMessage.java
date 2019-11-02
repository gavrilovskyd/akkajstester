package ru.labs.jstester.messages;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ErrorMessage {
    private String message;

    public ErrorMessage() {
        this.message = "";
    }
}

package ru.labs.jstester.messages;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResultRequest {
    private String packageID;

    public ResultRequest(String packageID) {
        this.packageID = packageID;
    }

    public String getPackageID() {
        return packageID;
    }
}

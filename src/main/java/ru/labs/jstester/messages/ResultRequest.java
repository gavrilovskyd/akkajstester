package ru.labs.jstester.messages;

public class ResultRequest {
    private String packageID;

    public ResultRequest(String packageID) {
        this.packageID = packageID;
    }

    public String getPackageID() {
        return packageID;
    }
}

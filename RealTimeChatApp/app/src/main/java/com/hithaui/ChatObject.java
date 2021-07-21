package com.hithaui;

public class ChatObject {
    private String fullName;
    private String message;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatObject() {
    }

    public ChatObject(String fullName, String message) {
        this.fullName = fullName;
        this.message = message;
    }
}

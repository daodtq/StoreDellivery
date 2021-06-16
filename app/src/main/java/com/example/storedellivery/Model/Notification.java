package com.example.storedellivery.Model;

public class Notification {
    private boolean error;
    private String message;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Notification() {
    }

    public Notification(boolean error, String message) {
        this.error = error;
        this.message = message;
    }
}

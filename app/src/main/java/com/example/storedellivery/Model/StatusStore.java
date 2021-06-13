package com.example.storedellivery.Model;

public class StatusStore {
    private boolean error;
    private String message;

    public boolean getError() {
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

    public StatusStore() {
    }

    public StatusStore(boolean error, String message) {
        this.error = error;
        this.message = message;
    }
}

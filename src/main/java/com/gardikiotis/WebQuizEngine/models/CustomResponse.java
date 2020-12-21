package com.gardikiotis.WebQuizEngine.models;

public class CustomResponse {
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomResponse(String message) {
        this.message = message;
    }

    public CustomResponse() {
    }
}

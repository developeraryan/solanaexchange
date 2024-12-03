package com.solanaexchange.project.model;

public class UserResponseModel {

    private String message;
    private String email;


    public UserResponseModel() {
super();
    }

    public UserResponseModel(String message, String email) {
        this.message = message;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserResponseModel{" +
                "message='" + message + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

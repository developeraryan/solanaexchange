package com.solanaexchange.project.model;

import lombok.Data;

@Data
public class UserRequestModel {

    private String email;
    private String password;
    private String deviceName;
    private String ipAddr;
    private String location;
    private String referralCode;
    public UserRequestModel() {
        super();
    }

    public UserRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRequestModel{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

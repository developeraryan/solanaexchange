package com.solanaexchange.project.model;

import lombok.Data;

@Data
public class UpdatePassword {
    private String email;
    private String oldPassword;
    private String newPassword;
}

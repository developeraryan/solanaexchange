package com.solanaexchange.project.model;

import lombok.Data;

@Data
public class UpdateEmail {
    private String oldEmail;
    private String newEmail;
}

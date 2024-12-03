package com.solanaexchange.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="LOGIN_HISTORY")
@Data
public class LoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="EMAIL")
    private String email;
    @Column(name="LOGIN_TIME")
    private String loginTime;
    @Column(name="IP_ADDR")
    private String ipAddr;
    @Column(name="DEVICE_NAME")
    private String deviceName;
    @Column(name="LOCATION")
    private String location;
}

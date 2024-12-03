package com.solanaexchange.project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="USERS")
public class Users {
    @Id
    @Column(name="USER_ID")
    private String id;
    @Column(name="EMAIL")
    private String email;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="REFER_CODE")
    private String referCode;
    @Column(name="REFERRAL_POINTS")
    private int referralPoints;

    @Column(name="NO_OF_REFERS")
    private int noOfRefers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wallet> wallets;

    public Users() {
        super();
    }

    public Users(String id, String email, String password, String referCode, int referralPoints, int noOfRefers) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.referCode = referCode;
        this.referralPoints = referralPoints;
        this.noOfRefers = noOfRefers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    public double getReferralPoints() {
        return referralPoints;
    }

    public void setReferralPoints(int referralPoints) {
        this.referralPoints = referralPoints;
    }

    public double getNoOfRefers() {
        return noOfRefers;
    }

    public void setNoOfRefers(int noOfRefers) {
        this.noOfRefers = noOfRefers;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", referCode='" + referCode + '\'' +
                ", referralPoints='" + referralPoints + '\'' +
                ", noOfRefers='" + noOfRefers + '\'' +
                '}';
    }
}

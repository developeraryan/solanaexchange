package com.solanaexchange.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="WALLET")
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="WALLET_ID")
    private int id;
    @Column(name="EMAIL")
    private String email;
    @Column(name="WALLET_NUMBER")
    private String walletNumber;

    @Column(name="WALLET_TYPE")
    private String walletTYPE;

    @Column(name="WALLET_BALANCE")
    private String walletBalance;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Users user;
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CryptoBalances> cryptoBalances;
    public Wallet() {
    }

    public Wallet(String email, String walletNumber, String walletTYPE, String walletBalance, Users user) {
        this.email = email;
        this.walletNumber = walletNumber;
        this.walletTYPE = walletTYPE;
        this.walletBalance = walletBalance;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", walletNumber='" + walletNumber + '\'' +
                ", walletTYPE='" + walletTYPE + '\'' +
                ", walletBalance='" + walletBalance + '\'' +
                '}';
    }
}

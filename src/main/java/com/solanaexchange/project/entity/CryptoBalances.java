package com.solanaexchange.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="CRYPTO_BALANCE")
@Data
public class CryptoBalances {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CRYPTO_BALANCE_ID")
    private int id;
    @Column(name="EMAIL")
    private String email;
    @Column(name="WALLET_NUMBER")
    private String walletNumber;
    @Column(name="WALLET_TYPE")
    private String walletType;
    @Column(name="CURRENCY")
    private String currency;
    @Column(name="FUND_AVL_BAL")
    private String fundAvlBal;
    @Column(name="FUND_LOCK_BAL")
    private String fundLockBal;
    @Column(name="FUND_TOTAL_BAL")
    private String fundTotalBal;
    @Column(name="SPOT_BAL")
    private String spotBal;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Wallet wallet;

}

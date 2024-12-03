package com.solanaexchange.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Table(name = "TXN_INTERWALLET")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TxnInterWallet {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "TXN_ID")
    private int orderId;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "AMOUNT")
    private String amount;
    @Column(name = "FROM_WALLET_TYPE")
    private String fromWalletType;
    @Column(name = "TO_WALLET_TYPE")
    private String toWalletType;
    @Column(name = "CURRENCY")
    private String currency;
    public TxnInterWallet(String email, String amount, String fromWalletType, String toWalletType, String currency) {
        this.email = email;
        this.amount = amount;
        this.fromWalletType = fromWalletType;
        this.toWalletType = toWalletType;
        this.currency = currency;
    }
}

package com.solanaexchange.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "TXN_P2P")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TxnP2P {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "TXN_ID")
    private int orderId;
    @Column(name = "AMOUNT")
    private String amount;
    @Column(name = "FROM_EMAIL")
    private String fromEmail;
    @Column(name = "TO_EMAIL")
    private String toEmail;
    @Column(name = "CURRENCY")
    private String currency;
    public TxnP2P(String fromEmail, String amount, String toEmail, String currency) {
        this.fromEmail = fromEmail;
        this.amount = amount;
        this.toEmail = toEmail;
        this.currency = currency;
    }
}

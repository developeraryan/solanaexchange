package com.solanaexchange.project.model;

import lombok.Data;

@Data
public class TransactionRequestModel {
    private String amount;
    private String txnType;
    private String email;
    private String fromWalletType;
    private String toWalletType;
    private String currency;

}

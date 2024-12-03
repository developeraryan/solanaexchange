package com.solanaexchange.project.model;

import lombok.Data;

@Data
public class TxnStakingRequestModel {
    private String email;
    private String stakeAmount;
    private String currency;
}

package com.solanaexchange.project.controller;

import com.solanaexchange.project.model.TransactionP2PRequestModel;
import com.solanaexchange.project.model.TransactionRequestModel;
import com.solanaexchange.project.model.TxnHistEmail;
import com.solanaexchange.project.model.TxnStakingRequestModel;
import com.solanaexchange.project.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/txn")
@RestController
@CrossOrigin
public class TxnController {
    @Autowired
    TxnService txnService;
    @PostMapping("/interwallet")
    public Map<String,Object> performTxn(@RequestBody TransactionRequestModel transactionRequestModel){
        return txnService.performTxn(transactionRequestModel);
    }

    @PostMapping("/p2p")
    public Map<String,Object> performP2PTxn(@RequestBody TransactionP2PRequestModel transactionP2PRequestModel){
        return txnService.performP2PTxn(transactionP2PRequestModel);
    }

    @PostMapping("/staking")
    public Map<String,Object> performStaking(@RequestBody TxnStakingRequestModel txnStakingRequestModel){
        return txnService.performStaking(txnStakingRequestModel);
    }
    @PostMapping("/unstaking")
    public Map<String,Object> performUnstaking(@RequestBody TxnStakingRequestModel txnStakingRequestModel){
        return txnService.performUnstaking(txnStakingRequestModel);
    }
    @PostMapping("/interwallethist")
    public Map<String,Object> interwallethist(@RequestBody TxnHistEmail txnHistEmail){
        return txnService.interWalletHist(txnHistEmail);
    }
}

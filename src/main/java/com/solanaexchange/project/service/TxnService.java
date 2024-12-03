package com.solanaexchange.project.service;

import com.solanaexchange.project.dao.CryptoBalancesRepo;
import com.solanaexchange.project.dao.TxnInterWalletRepo;
import com.solanaexchange.project.dao.WalletRepo;
import com.solanaexchange.project.entity.CryptoBalances;
import com.solanaexchange.project.entity.TxnInterWallet;
import com.solanaexchange.project.entity.Wallet;
import com.solanaexchange.project.model.TransactionP2PRequestModel;
import com.solanaexchange.project.model.TransactionRequestModel;
import com.solanaexchange.project.model.TxnHistEmail;
import com.solanaexchange.project.model.TxnStakingRequestModel;
import jakarta.transaction.Transactional;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TxnService {

    @Autowired
    WalletRepo walletRepo;
    @Autowired
    TxnInterWalletRepo txnInterWalletRepo;
    @Autowired
    CryptoBalancesRepo cryptoBalancesRepo;

    @Transactional
    public Map<String,Object> performTxn(TransactionRequestModel transactionRequestModel) {
        int amt = Integer.parseInt(transactionRequestModel.getAmount());
        Map<String, Object> map = new HashMap<>();
        if (transactionRequestModel.getFromWalletType().equals("FUND") && transactionRequestModel.getToWalletType().equals("SPOT")) {
            Optional<CryptoBalances> fundWallet = Optional.ofNullable(cryptoBalancesRepo.findEmailAndWallettypeAndCurrency(transactionRequestModel.getEmail(), transactionRequestModel.getFromWalletType(), transactionRequestModel.getCurrency()));
            Optional<CryptoBalances> spotWallet = Optional.ofNullable(cryptoBalancesRepo.findEmailAndWallettypeAndCurrency(transactionRequestModel.getEmail(), transactionRequestModel.getToWalletType(), transactionRequestModel.getCurrency()));
            if (fundWallet.isEmpty()) {
                map.put("status", "0");
                map.put("message", "No wallet found for the fundWallet email");
                return map;
            }
            if (spotWallet.isEmpty()) {
                map.put("status", "0");
                map.put("message", "No wallet found for the spotWallet email");
                return map;
            }
            Integer fundWalletBal = 0, spotWalletBal = 0;
            if (Integer.parseInt(fundWallet.get().getFundAvlBal()) < amt) {
                map.put("status", "0");
                map.put("message", "Not enough balance to perform this transaction");
                return map;
            }
            fundWalletBal = Integer.parseInt(fundWallet.get().getFundAvlBal()) - amt;
            spotWalletBal = Integer.parseInt(spotWallet.get().getSpotBal()) + amt;

            cryptoBalancesRepo.updateFundBalEmailAndWallettypeAndCurrency(transactionRequestModel.getEmail(), transactionRequestModel.getFromWalletType(), transactionRequestModel.getCurrency(), fundWalletBal.toString());
            cryptoBalancesRepo.updateSpotBalEmailAndWallettypeAndCurrency(transactionRequestModel.getEmail(), transactionRequestModel.getToWalletType(), transactionRequestModel.getCurrency(), spotWalletBal.toString());

            List<CryptoBalances> fundBalancesList = cryptoBalancesRepo.findByEmailAndWallettype(transactionRequestModel.getEmail(),"FUND");
            List<CryptoBalances> spotBalancesList = cryptoBalancesRepo.findByEmailAndWallettype(transactionRequestModel.getEmail(),"SPOT");
            map.put("fundBalancesList", fundBalancesList);
            map.put("spotBalancesList",spotBalancesList);

            int fundWalletBall = cryptoBalancesRepo.findTotalFundBal(transactionRequestModel.getEmail(),"FUND");
            map.put("fundWalletNumberBalance",fundWalletBall);
            int spotWalletBall = cryptoBalancesRepo.findTotalSpotBal(transactionRequestModel.getEmail(),"SPOT");
            map.put("spotWalletNumberBalance",spotWalletBall);


        }
        else if (transactionRequestModel.getFromWalletType().equals("SPOT") && transactionRequestModel.getToWalletType().equals("FUND")) {
            Optional<CryptoBalances> spotWallet = Optional.ofNullable(cryptoBalancesRepo.findEmailAndWallettypeAndCurrency(transactionRequestModel.getEmail(), transactionRequestModel.getFromWalletType(), transactionRequestModel.getCurrency()));
            Optional<CryptoBalances> fundWallet = Optional.ofNullable(cryptoBalancesRepo.findEmailAndWallettypeAndCurrency(transactionRequestModel.getEmail(), transactionRequestModel.getToWalletType(), transactionRequestModel.getCurrency()));
            Integer fundWalletBal = 0, spotWalletBal = 0;
            if (fundWallet.isEmpty()) {
                map.put("status", "0");
                map.put("message", "No wallet found for the fundWallet email");
                return map;
            }
            if (spotWallet.isEmpty()) {
                map.put("status", "0");
                map.put("message", "No wallet found for the spotWallet email");
                return map;
            }
            if (Integer.parseInt(spotWallet.get().getSpotBal()) < amt) {
                map.put("status", "0");
                map.put("message", "Not enough balance to perform this transaction");
                return map;
            }

            spotWalletBal = Integer.parseInt(spotWallet.get().getSpotBal()) - amt;
            fundWalletBal = Integer.parseInt(fundWallet.get().getFundAvlBal()) + amt;

            cryptoBalancesRepo.updateFundBalEmailAndWallettypeAndCurrency(transactionRequestModel.getEmail(), transactionRequestModel.getToWalletType(), transactionRequestModel.getCurrency(), fundWalletBal.toString());
            cryptoBalancesRepo.updateSpotBalEmailAndWallettypeAndCurrency(transactionRequestModel.getEmail(), transactionRequestModel.getFromWalletType(), transactionRequestModel.getCurrency(), spotWalletBal.toString());

            List<CryptoBalances> fundBalancesList = cryptoBalancesRepo.findByEmailAndWallettype(transactionRequestModel.getEmail(),"FUND");
            List<CryptoBalances> spotBalancesList = cryptoBalancesRepo.findByEmailAndWallettype(transactionRequestModel.getEmail(),"SPOT");
            map.put("fundBalancesList", fundBalancesList);
            map.put("spotBalancesList",spotBalancesList);

            int fundWalletBall = cryptoBalancesRepo.findTotalFundBal(transactionRequestModel.getEmail(),"FUND");
            map.put("fundWalletNumberBalance",fundWalletBall);
            int spotWalletBall = cryptoBalancesRepo.findTotalSpotBal(transactionRequestModel.getEmail(),"SPOT");
            map.put("spotWalletNumberBalance",spotWalletBall);


        }

        map.put("txnDetails", "Txn Success");
        return map;
    }

    public Map<String, Object> performP2PTxn(TransactionP2PRequestModel transactionP2PRequestModel) {
        int amt = Integer.parseInt(transactionP2PRequestModel.getAmount());
        String sender = transactionP2PRequestModel.getFromEmail();
        String receiver = transactionP2PRequestModel.getToEmail();
        Map<String,Object> map = new HashMap<>();
        Optional<List<Wallet>> walletList = Optional.ofNullable(walletRepo.findByEmail(transactionP2PRequestModel.getFromEmail()));
        return map;

    }

    public Map<String, Object> interWalletHist(TxnHistEmail txnHistEmail) {
        String email = txnHistEmail.getEmail();
        Map<String,Object> map = new HashMap<>();
        Optional<List<TxnInterWallet>> txnInterWalletList = Optional.ofNullable(txnInterWalletRepo.findByEmail(email));
        if(txnInterWalletList.isEmpty()){
            map.put("status","0");
            map.put("message","NO transactions found");
        }
        map.put("status","1");
        map.put("txns",txnInterWalletList);
        return map;

    }

    public Map<String, Object> performStaking(TxnStakingRequestModel txnStakingRequestModel) {
        CryptoBalances cryptoBalances = cryptoBalancesRepo.findByEmailAndWallettypeAndCurrency(txnStakingRequestModel.getEmail(),"FUND",txnStakingRequestModel.getCurrency());

        Map<String, Object> stakemap = new HashMap<>();
        if(Integer.parseInt(txnStakingRequestModel.getStakeAmount()) > Integer.parseInt(cryptoBalances.getFundAvlBal())){
            stakemap.put("status",0);
            stakemap.put("message","The available amount is less than stake amount");
            return stakemap;
        }
        Integer fundAvlBal = Integer.parseInt(cryptoBalances.getFundAvlBal()) - Integer.parseInt(txnStakingRequestModel.getStakeAmount());
        Integer fundStakeBal = Integer.parseInt(cryptoBalances.getFundLockBal()) + Integer.parseInt(txnStakingRequestModel.getStakeAmount());

        cryptoBalances.setFundAvlBal(fundAvlBal.toString());
        cryptoBalances.setFundLockBal(fundStakeBal.toString());

        cryptoBalancesRepo.save(cryptoBalances);

        List<CryptoBalances> fundBalancesList = cryptoBalancesRepo.findByEmailAndWallettype(txnStakingRequestModel.getEmail(),"FUND");
        List<CryptoBalances> spotBalancesList = cryptoBalancesRepo.findByEmailAndWallettype(txnStakingRequestModel.getEmail(),"SPOT");
        stakemap.put("fundBalancesList", fundBalancesList);
        stakemap.put("spotBalancesList",spotBalancesList);

        int fundWalletBal = cryptoBalancesRepo.findTotalFundBal(txnStakingRequestModel.getEmail(),"FUND");
        stakemap.put("fundWalletNumberBalance",fundWalletBal);
        int spotWalletBal = cryptoBalancesRepo.findTotalSpotBal(txnStakingRequestModel.getEmail(),"SPOT");
        stakemap.put("spotWalletNumberBalance",spotWalletBal);

        stakemap.put("status",1);
        stakemap.put("message","The amount has been successfully staked");
        return stakemap;
    }
    @Transactional
    public Map<String, Object> performUnstaking(TxnStakingRequestModel txnStakingRequestModel) {
        CryptoBalances cryptoBalances = cryptoBalancesRepo.findByEmailAndWallettypeAndCurrency(txnStakingRequestModel.getEmail(),"FUND",txnStakingRequestModel.getCurrency());

        Map<String, Object> stakemap = new HashMap<>();
        if(Integer.parseInt(txnStakingRequestModel.getStakeAmount()) > Integer.parseInt(cryptoBalances.getFundLockBal())){
            stakemap.put("status",0);
            stakemap.put("message","Stake amount greater than lock amount");
            return stakemap;
        }
        Integer fundAvlBal = Integer.parseInt(cryptoBalances.getFundAvlBal()) + Integer.parseInt(txnStakingRequestModel.getStakeAmount());

        cryptoBalances.setFundLockBal("0");

        cryptoBalancesRepo.updateFundBalEmailWallettypeAndCurrency(txnStakingRequestModel.getEmail(),"FUND",txnStakingRequestModel.getCurrency(),fundAvlBal.toString());
        stakemap.put("status",1);
        stakemap.put("message","The amount has been successfully staked");
        List<CryptoBalances> fundBalancesList = cryptoBalancesRepo.findByEmailAndWallettype(txnStakingRequestModel.getEmail(),"FUND");
        List<CryptoBalances> spotBalancesList = cryptoBalancesRepo.findByEmailAndWallettype(txnStakingRequestModel.getEmail(),"SPOT");
        stakemap.put("fundBalancesList", fundBalancesList);
        stakemap.put("spotBalancesList",spotBalancesList);

        int fundWalletBal = cryptoBalancesRepo.findTotalFundBal(txnStakingRequestModel.getEmail(),"FUND");
        stakemap.put("fundWalletNumberBalance",fundWalletBal);
        int spotWalletBal = cryptoBalancesRepo.findTotalSpotBal(txnStakingRequestModel.getEmail(),"SPOT");
        stakemap.put("spotWalletNumberBalance",spotWalletBal);
        return stakemap;
    }

}

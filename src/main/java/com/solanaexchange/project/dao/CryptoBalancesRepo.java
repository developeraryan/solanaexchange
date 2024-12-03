package com.solanaexchange.project.dao;

import com.solanaexchange.project.entity.CryptoBalances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CryptoBalancesRepo extends JpaRepository<CryptoBalances,Integer> {
    @Query(value = "SELECT * from crypto_balance where email = :email and wallet_type=:walletType", nativeQuery = true)
    List<CryptoBalances> findByEmailAndWallettype(String email, String walletType);
    @Query(value = "SELECT * from crypto_balance where email = :email and wallet_type=:walletType and currency=:currency", nativeQuery = true)
    CryptoBalances findByEmailAndWallettypeAndCurrency(String email, String walletType, String currency);

    @Query(value = "SELECT * from crypto_balance where email = :email", nativeQuery = true)
    CryptoBalances[] findByEmail(String email);

    @Query(value = "SELECT * from crypto_balance where email = :email and wallet_type=:walletType and currency=:currency", nativeQuery = true)
    CryptoBalances findEmailAndWallettypeAndCurrency(String email, String walletType, String currency);
    @Modifying(clearAutomatically = true)
    @Query(value = "update crypto_balance set fund_avl_bal = :amt where email = :email and wallet_type=:walletType and currency=:currency", nativeQuery = true)
    void updateFundBalEmailAndWallettypeAndCurrency(String email, String walletType, String currency,String amt);

    @Modifying(clearAutomatically = true)
    @Query(value = "update crypto_balance set spot_bal = :amt where email = :email and wallet_type=:walletType and currency=:currency", nativeQuery = true)
    void updateSpotBalEmailAndWallettypeAndCurrency(String email, String walletType, String currency,String amt);

    @Modifying(clearAutomatically = true)
    @Query(value = "update crypto_balance set fund_Avl_bal = :amt where email = :email and wallet_type=:walletType and currency=:currency", nativeQuery = true)
    void updateFundBalEmailWallettypeAndCurrency(String email, String walletType, String currency,String amt);

    @Query(value = "SELECT sum(fund_avl_bal) from crypto_balance where email = :email and wallet_type=:walletType", nativeQuery = true)
    int findTotalFundBal(String email, String walletType);
    @Query(value = "SELECT sum(spot_bal) from crypto_balance where email = :email and wallet_type=:walletType", nativeQuery = true)
    int findTotalSpotBal(String email, String walletType);

}

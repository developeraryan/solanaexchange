package com.solanaexchange.project.dao;

import com.solanaexchange.project.entity.Users;
import com.solanaexchange.project.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepo extends JpaRepository<Wallet,String> {
    @Query(value = "SELECT * from wallet where email = :email", nativeQuery = true)
    List<Wallet> findByEmail(String email);

    @Query(value = "SELECT * from wallet where wallet_number = :walletNumber", nativeQuery = true)
    Wallet findByWalletNumber(String walletNumber);
}

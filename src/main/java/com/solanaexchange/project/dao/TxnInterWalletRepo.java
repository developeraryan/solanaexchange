package com.solanaexchange.project.dao;

import com.solanaexchange.project.entity.TxnInterWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TxnInterWalletRepo extends JpaRepository<TxnInterWallet, Integer> {
    @Query(value = "SELECT * from txn_interwallet where email = :email", nativeQuery = true)
    List<TxnInterWallet> findByEmail(String email);
}

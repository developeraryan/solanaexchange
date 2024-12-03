package com.solanaexchange.project.dao;

import com.solanaexchange.project.entity.CryptoBalances;
import com.solanaexchange.project.entity.LoginHistory;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistRepo extends JpaRepository<LoginHistory,String> {
    @Query(value = "SELECT * from login_history where email = :email", nativeQuery = true)
    LoginHistory[] findByEmail(String email);


}

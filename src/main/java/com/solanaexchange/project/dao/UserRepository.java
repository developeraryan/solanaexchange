package com.solanaexchange.project.dao;

import com.solanaexchange.project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT * from users where email = :email", nativeQuery = true)
    Users findByEmail(String email);

    @Query(value = "SELECT * from users where refer_code = :referralCode", nativeQuery = true)
    Users findByReferralCode(String referralCode);
}

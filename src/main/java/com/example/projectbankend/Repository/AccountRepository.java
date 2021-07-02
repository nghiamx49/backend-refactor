package com.example.projectbankend.Repository;


import com.example.projectbankend.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);

    @Modifying
    @Query(value = "INSERT INTO accounts" +
            "(username, password, address, phone_number, email, role_id, create_at)" +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7);", nativeQuery = true)
    void createAccount(String username, String password, String address, String phone_number, String email, int role_id, Date create_at);
}

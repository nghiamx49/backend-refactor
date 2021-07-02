package com.example.projectbankend.Repository;

import com.example.projectbankend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Query(value = "INSERT INTO users(full_name, zipcode, account_id, ban)" +
            " VALUES (?2, ?3, ?4, ?1) ", nativeQuery = true)
    void createUser(Boolean ban, String full_name, String zipcode, int account_id);

    List<User> findAllByBan(boolean status);

    @Modifying
    @Query(value = "UPDATE  users SET ban = true WHERE id = ?1", nativeQuery = true)
    void banUser(int id);
}

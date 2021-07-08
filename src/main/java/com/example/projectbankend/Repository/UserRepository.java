package com.example.projectbankend.Repository;

import com.example.projectbankend.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    @Modifying
    @Query(value = "INSERT INTO users(full_name, zipcode, account_id, ban)" +
            " VALUES (?2, ?3, ?4, ?1) ", nativeQuery = true)
    void createUser(Boolean ban, String full_name, String zipcode, int account_id);

    Page<User> findAllByBan(boolean status, Pageable pageable);

    User findById(int Id);

    int countAllByBan(boolean status);

    @Modifying
    @Query(value = "UPDATE  users SET full_name = ?2, zipcode = ?3 WHERE id = ?1",nativeQuery = true)
    void updateUser(int id, String full_name, String zip_code);

    @Modifying
    @Query(value = "UPDATE  users SET ban = true WHERE id = ?1", nativeQuery = true)
    void banUser(int id);
}

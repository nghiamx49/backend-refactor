package com.example.projectbankend.Repository;

import com.example.projectbankend.Models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    @Modifying
    @Query(value = "INSERT  INTO providers(bank_account_number, owner, status, store_name, account_id, bank_id)" +
            "VALUES (?1, ?2, 'Pending', ?3, ?4, 1)", nativeQuery = true)
    void createProvider(String bank_account_number, String owner, String store_name, int account_id);

    List<Provider> findAllByStatus(String status);

    @Query(value = "UPDATE providers SET status = ?2 WHERE id = ?1", nativeQuery = true)
    @Modifying
    void updateRegisterStatus(int id, String status);
}

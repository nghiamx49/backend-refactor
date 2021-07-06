package com.example.projectbankend.Repository;

import com.example.projectbankend.Models.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ProviderRepository extends PagingAndSortingRepository<Provider, Integer> {
    @Modifying
    @Query(value = "INSERT  INTO providers(owner, status, store_name, account_id, bank_id)" +
            "VALUES (?1, 'Pending', ?2, ?3, 1)", nativeQuery = true)
    void createProvider(String owner, String store_name, int account_id);

    Provider findById(int id);

    int countProviderByStatus(String status);

    Page<Provider> findAllByStatus(String status, Pageable pageable);

    @Query(value = "UPDATE providers SET status = ?2 WHERE id = ?1", nativeQuery = true)
    @Modifying
    void updateRegisterStatus(int id, String status);
}

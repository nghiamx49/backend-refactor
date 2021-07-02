package com.example.projectbankend.Repository;

import com.example.projectbankend.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByStatus(String status);
    @Query(value = "UPDATE  products SET status = ?2 WHERE id = ?1", nativeQuery = true)
    @Modifying
    void updateProductStatus(int id, String status);
}

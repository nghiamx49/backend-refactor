package com.example.projectbankend.Repository;

import com.example.projectbankend.Models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ImageRepository extends JpaRepository<ProductImage, Integer> {
    @Query(value = "INSERT INTO product_images(image_source, product_id) " +
            "VALUES(?1, ?2)", nativeQuery = true)
    @Modifying
    void createProductImage(String image_source, int product_id);
}

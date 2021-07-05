package com.example.projectbankend.Repository;

import com.example.projectbankend.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    Product findById(int id);

    Product findByNameAndProviderId(String name, int provider_id);

    @Query(value = "SELECT  * FROM products WHERE status = ?1 AND (LOWER(unaccent(name)) LIKE  unaccent(?2) OR LOWER(unaccent(product_description)) LIKE unaccent(?2))", nativeQuery = true)
    Page<Product> findAllByStatus(String status,String keyword, Pageable pageable);

    Page<Product> findByStatusAndProviderId(String status, int provider_id, Pageable pageable);

    @Query(value = "UPDATE  products SET status = ?2 WHERE id = ?1", nativeQuery = true)
    @Modifying
    void updateProductStatus(int id, String status);

    @Query(value = "INSERT  INTO products(name, number_of_sold, product_description, quantity, status, unit_price, category_id, provider_id) " +
            "VALUES (?1, 0, ?2, ?3, 'Pending', ?4, ?5, ?6)", nativeQuery = true)
    @Modifying
    void createNewProduct(String name, String product_description, int quantity, String unit_price, int category_id, int provider_id);

    @Query(value = "UPDATE products SET product_description=?1, quantity = ?2 WHERE id = ?3",nativeQuery = true)
    @Modifying
    void updateProduct(String product_description, int quantity, int product_id);
}

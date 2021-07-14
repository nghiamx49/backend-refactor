package com.example.projectbankend.Repository;

import com.example.projectbankend.DTO.CategoryDTO;
import com.example.projectbankend.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);

    @Query(value = "SELECT new com.example.projectbankend.DTO.CategoryDTO(category.id, category.name) FROM Category category")
    List<CategoryDTO> findAllCategories();
}

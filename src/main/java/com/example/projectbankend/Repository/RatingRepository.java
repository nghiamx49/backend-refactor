package com.example.projectbankend.Repository;

import com.example.projectbankend.DTO.RateDTO;
import com.example.projectbankend.Models.Rate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public interface RatingRepository extends PagingAndSortingRepository<Rate, Integer> {
    List<Rate> findAllByProductId(int id);



    @Query(value = "INSERT INTO rates(comment, create_at, star, product_id, user_id) " +
            "VALUES (?3, ?5, ?4, ?2, ?1)",nativeQuery = true)
    @Modifying
    void createRating(int userId, int productId, String comment, int star, Date create_at);
}

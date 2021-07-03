package com.example.projectbankend.Repository;

import com.example.projectbankend.DTO.RateDTO;
import com.example.projectbankend.Models.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RatingRepository extends PagingAndSortingRepository<Rate, Integer> {
    @Query(value = "SELECT new com.example.projectbankend.DTO.RateDTO(rate.id, rate.user.full_name, rate.comment, rate.create_at, rate.star)" +
            " FROM Rate rate WHERE rate.product.id = ?1", nativeQuery = false)
    List<RateDTO> findAllByProductId(int id);
}

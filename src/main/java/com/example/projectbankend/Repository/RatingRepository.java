package com.example.projectbankend.Repository;

import com.example.projectbankend.Models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RatingRepository extends PagingAndSortingRepository<Rate, Integer> {
}

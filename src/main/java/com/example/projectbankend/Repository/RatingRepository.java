package com.example.projectbankend.Repository;

import com.example.projectbankend.Models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rate, Integer> {
}

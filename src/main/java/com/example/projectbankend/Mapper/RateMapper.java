package com.example.projectbankend.Mapper;

import com.example.projectbankend.DTO.RateDTO;
import com.example.projectbankend.Models.Rate;

public class RateMapper {
    public static RateDTO ratingDTO(Rate rate) {
        RateDTO rateDTO = new RateDTO();
        rateDTO.setId(rate.getId());
        rateDTO.setStar(rate.getStar());
        rateDTO.setUser_full_name(rate.getUser().getFull_name());
        rateDTO.setCreate_at(rate.getCreate_at());
        rateDTO.setComment(rate.getComment());
        return  rateDTO;
    }
}

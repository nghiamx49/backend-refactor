package com.example.projectbankend.DTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ProductDetailDTO {
    private int id;
    private String name;
    private int product_quantity;
    private String unit_price;
    private int number_of_sold;
    private ArrayList<ImageDTO> image_sources;
    private String provider_name;
    private ArrayList<RateDTO> rating;
}

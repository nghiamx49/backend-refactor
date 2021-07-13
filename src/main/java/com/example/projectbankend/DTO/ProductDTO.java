package com.example.projectbankend.DTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private int product_quantity;
    private String unit_price;
    private int number_of_sold;
    private String image_source;
    private String provider_name;
    private String category_name;
}


package com.example.projectbankend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private int id;
    private String product_name;
    private String store_name;
    private int quantity_purchased;
    private String unit_price;
}
package com.example.projectbankend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private int id;
    private String product_name;
    private String store_name;
    private int quantity_purchased;
    private String unit_price;
    private String payment_method;
    private Date date_of_payment;
}

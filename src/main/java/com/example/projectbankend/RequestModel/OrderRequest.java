package com.example.projectbankend.RequestModel;

import lombok.Data;

@Data
public class OrderRequest {
    private int product_id;
    private int quantity_purchased;
}

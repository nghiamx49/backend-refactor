package com.example.projectbankend.RequestModel;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderRequest {
    @NotNull(message = "cần chọn sản phẩm")
    private int product_id;
    @NotNull(message = "chọn số lượng")
    @Min(1)
    private int quantity_purchased;
}

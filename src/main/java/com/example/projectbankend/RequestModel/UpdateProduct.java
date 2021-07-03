package com.example.projectbankend.RequestModel;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateProduct {
    @NotNull(message = "id không thể trống")
    private int id;
    @NotBlank(message = "mô ta không được để trống")
    private String product_description;
    @NotNull(message = "số lượng không được để trống")
    private int quantity;
}

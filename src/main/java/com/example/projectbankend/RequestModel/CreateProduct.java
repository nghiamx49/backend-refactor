package com.example.projectbankend.RequestModel;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
public class CreateProduct {
    @NotBlank(message = "tên sản phẩm không được để trống")
    private String name;
    @NotNull(message = "số lượng không được để trống")
    private int quantity;
    @NotNull(message = "danh mục sản phẩm không được để trống")
    private int category_id;
    @NotBlank(message = "giá sản phẩm không được để trống")
    private String unit_price;
    @NotBlank(message = "mô tả không được để trống")
    private String product_description;
    @Size(min = 1, max = 4, message = "sản phẩm cần tối thiểu 1 ảnh và tối đa 4 ảnh")
    private ArrayList<String> images;
}

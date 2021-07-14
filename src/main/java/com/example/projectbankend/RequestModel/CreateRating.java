package com.example.projectbankend.RequestModel;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateRating {
    @NotNull(message = "phải chọn sản phẩm để đánh giá")
    private int product_id;
    @NotNull(message = "số lượng sao phải chọn")
    private int star;
    @NotBlank(message = "bình luận không được trống")
    private String comment;
}

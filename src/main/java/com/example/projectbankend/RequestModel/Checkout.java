package com.example.projectbankend.RequestModel;

import com.example.projectbankend.Models.Validator.PaymentType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Checkout {
    @NotNull(message = "vui lòng chọn sản phẩm")
    private int product_id;
    @PaymentType
    private String method;
}

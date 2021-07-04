package com.example.projectbankend.RequestModel;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateUser {
    @NotBlank(message = "tên không được trống")
    private String full_name;
    @NotBlank(message = "zipcode không được trống")
    private String zipcode;
    private String avatar_source;
    @NotBlank(message = "địa chỉ không được trống")
    private String address;
    @NotBlank(message = "số điện thoại không được trống")
    private String phone_number;
}

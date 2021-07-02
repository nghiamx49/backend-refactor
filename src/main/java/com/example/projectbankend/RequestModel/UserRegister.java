package com.example.projectbankend.RequestModel;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegister {
    @NotBlank(message = "tên đăng nhập không thể để trống")
    private String username;
    @NotBlank(message = "mật khẩu không thể để trống")
    private String password;
    @NotBlank(message = "địa chỉ không thể để trống")
    private String address;
    @NotBlank(message = "số điện thoại không thể để trống")
    private String phone_number;
    @NotBlank(message = "email không thể để trống")
    private String email;
    @NotBlank(message = "họ tên không thể để trống")
    private String full_name;
    @NotBlank(message = "zipcode không thể để trống")
    private String zipcode;
}

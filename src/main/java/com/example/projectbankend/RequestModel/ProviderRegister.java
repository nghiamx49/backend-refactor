package com.example.projectbankend.RequestModel;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProviderRegister {
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
    @NotBlank(message = "tên cửa hàng không thể để trống")
    private String store_name;
    @NotBlank(message = "chủ sở hữu không thể để trống")
    private String owner;
}

package com.example.projectbankend.RequestModel;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "tên đăng nhập không thể để trống")
    private String username;
    @NotBlank(message = "mật khẩu không thể để trống")
    private String password;
}

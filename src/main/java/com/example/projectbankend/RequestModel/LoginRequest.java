package com.example.projectbankend.RequestModel;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "ten dang nhap khong the de trong")
    private String username;
    @NotBlank(message = "mat khau khong the de trong")
    private String password;
}

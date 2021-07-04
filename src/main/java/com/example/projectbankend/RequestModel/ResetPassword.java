package com.example.projectbankend.RequestModel;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ResetPassword {
    @NotBlank(message = "mật khẩu mới không được bỏ trống")
    private String new_password;
    private String username;
}

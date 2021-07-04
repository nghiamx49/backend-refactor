package com.example.projectbankend.RequestModel;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePassword {
    @NotBlank(message = "mật khẩu cũ không được bỏ trống")
    private String old_password;
    @NotBlank(message = "mật khẩu mới không được bỏ trống")
    private String new_password;
}

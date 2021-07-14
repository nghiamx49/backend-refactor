package com.example.projectbankend.RequestModel;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class VerifyOTP {

    @NotBlank(message = "otp không được bỏ trống")
    private String otp;
    private String username;
}

package com.example.projectbankend.RequestModel;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VerifyMail {
    @NotBlank(message = "email cần được nhập")
    private String email;
}

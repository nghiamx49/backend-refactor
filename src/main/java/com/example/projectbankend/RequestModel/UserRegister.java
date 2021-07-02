package com.example.projectbankend.RequestModel;

import lombok.Data;

@Data
public class UserRegister {
    private String username;
    private String password;
    private String address;
    private String phone_number;
    private String email;
    private String full_name;
    private String zipcode;
}

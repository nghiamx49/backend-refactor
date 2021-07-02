package com.example.projectbankend.RequestModel;

import lombok.Data;

@Data
public class ProviderRegister {
    private String username;
    private String password;
    private String address;
    private String phone_number;
    private String email;
    private String store_name;
    private String owner;
    private String bank_account_number;
}

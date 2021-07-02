package com.example.projectbankend.DTO;

import lombok.Data;

@Data
public class ProviderDTO {
    private int id;
    private String username;
    private String avatar_source;
    private String address ;
    private String email ;
    private String phone_number;
    private String store_name;
    private String owner ;
    private String bank ;
    private String bank_account_number;
}

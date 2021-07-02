package com.example.projectbankend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String username;
    private String avatar_source;
    private String address;
    private String phone_number;
    private String email;
    private String role;
}


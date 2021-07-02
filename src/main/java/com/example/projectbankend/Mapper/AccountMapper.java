package com.example.projectbankend.Mapper;

import com.example.projectbankend.DTO.AccountDTO;
import com.example.projectbankend.Models.Account;

public class AccountMapper {
    public static AccountDTO accountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAddress(account.getAddress());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setAvatar_source(account.getAvatar_source());
        accountDTO.setPhone_number(account.getPhone_number());
        accountDTO.setRole(account.getRole().getName());
        return  accountDTO;
    }
}

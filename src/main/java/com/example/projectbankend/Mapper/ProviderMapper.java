package com.example.projectbankend.Mapper;

import com.example.projectbankend.DTO.ProviderDTO;
import com.example.projectbankend.Models.Provider;

public class ProviderMapper {
    public static ProviderDTO toProviderDTO(Provider provider) {
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setId(provider.getId());
        providerDTO.setUsername(provider.getAccount().getUsername());
        providerDTO.setAddress(provider.getAccount().getAddress());
        providerDTO.setBank(provider.getBank().getName());
        providerDTO.setAvatar_source(provider.getAccount().getAvatar_source());
        providerDTO.setEmail(provider.getAccount().getEmail());
        providerDTO.setPhone_number(provider.getAccount().getPhone_number());
        providerDTO.setOwner(provider.getOwner());
        providerDTO.setBank_account_number(provider.getBank_account_number());
        providerDTO.setStore_name(provider.getStore_name());
        return providerDTO;
    }
}

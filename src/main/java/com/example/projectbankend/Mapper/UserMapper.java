package com.example.projectbankend.Mapper;

import com.example.projectbankend.DTO.UserDTO;
import com.example.projectbankend.Models.User;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getAccount().getUsername());
        userDTO.setAvatar_source(user.getAccount().getAvatar_source());
        userDTO.setAddress(user.getAccount().getAddress());
        userDTO.setPhone_number(user.getAccount().getPhone_number());
        userDTO.setCreate_at(user.getAccount().getCreate_at());
        userDTO.setRole(user.getAccount().getRole().getName());
        userDTO.setFull_name(user.getFull_name());
        userDTO.setBan(user.getBan());
        userDTO.setZipcode(user.getZipcode());

        return userDTO;
    }
}

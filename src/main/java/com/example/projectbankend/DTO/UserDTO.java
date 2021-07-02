package com.example.projectbankend.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Integer id ;
    private String username ;
    private String avatar_source ;
    private String address ;
    private String phone_number ;
    private Date create_at ;
    private String role ;
    private String full_name ;
    private Boolean ban ;
    private String  zipcode ;
}

package com.example.projectbankend.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserDTO {
    private int id ;
    private String username;
    private String avatar_source;
    private String address ;
    private String phone_number ;
    private Date create_at ;
    private String role ;
    @NotBlank
    private String full_name ;
    private Boolean ban ;
    private String zipcode ;
}

package com.example.projectbankend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO {
    private int id;
    private String user_full_name;
    private String comment;
    private Date create_at;
    private int star;
}

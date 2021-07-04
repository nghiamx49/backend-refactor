package com.example.projectbankend.RequestModel;

import lombok.Data;

@Data
public class CreateRating {
    private int productId;
    private int star;
    private String comment;
}

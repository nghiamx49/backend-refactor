package com.example.projectbankend.RequestModel;

import lombok.Data;
import com.example.projectbankend.Models.Validator.Status;

@Data
public class UpdateStatus {
    private int id;
    @Status
    private String status;
}

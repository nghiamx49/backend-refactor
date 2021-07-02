package com.example.projectbankend.RequestModel;

import lombok.Data;
import smartdev.backend.smartdev_backend.Models.Validator.Status;

@Data
public class UpdateStatus {
    private int id;
    @Status
    private String status;
}

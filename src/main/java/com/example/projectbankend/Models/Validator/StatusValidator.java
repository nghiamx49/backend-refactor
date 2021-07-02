package com.example.projectbankend.Models.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class StatusValidator implements ConstraintValidator<Status, String> {
    List<String> status = Arrays.asList("Pending", "Rejected", "Allowed");
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return status.contains(s);
    }
}

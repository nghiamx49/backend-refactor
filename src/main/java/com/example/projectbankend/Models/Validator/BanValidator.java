package com.example.projectbankend.Models.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class BanValidator implements ConstraintValidator<Ban, String> {
    List<String> list = Arrays.asList("ban", "not_ban");
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return list.contains(value);
    }
}

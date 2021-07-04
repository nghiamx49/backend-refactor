package com.example.projectbankend.Models.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PaymentValidator implements ConstraintValidator<PaymentType, String> {
        List<String> list = Arrays.asList("COD", "Paypal");
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
                return list.contains(value);
        }
}

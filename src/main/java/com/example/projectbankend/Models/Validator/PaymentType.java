package com.example.projectbankend.Models.Validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PaymentValidator.class)
public @interface PaymentType {
    String message() default "phương thức thanh toán không hợp lệ (COD hoặc Paypal)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

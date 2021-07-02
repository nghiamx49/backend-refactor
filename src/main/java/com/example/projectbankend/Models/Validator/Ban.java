package com.example.projectbankend.Models.Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BanValidator.class)
public @interface Ban {
    String message() default "Status không hợp lệ (ban hoặc not_ban)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

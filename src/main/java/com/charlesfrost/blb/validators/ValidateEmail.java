package com.charlesfrost.blb.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.Valid;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyEmailValidator.class)
public @interface ValidateEmail {
    String message() default "Niepoprawny email!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

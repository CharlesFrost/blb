package com.charlesfrost.blb.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Constraint(validatedBy = TeamsValidator.class)
public @interface ValidateTeams {
    String message() default "Teamy muszą być różne!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package com.charlesfrost.blb.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {
    String message() default "Hasła nie pasują do siebie!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

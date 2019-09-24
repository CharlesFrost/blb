package com.charlesfrost.blb.validators;

import com.charlesfrost.blb.models.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

   @Override
   public void initialize(PasswordMatches constraintAnnotation) {

   }

   @Override
   public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
      User user = (User) o;
      return user.getPassword().equals(user.getPasswordMatcher());
   }
}

package com.charlesfrost.blb.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyEmailValidator implements ConstraintValidator<ValidateEmail, String> {
   @Override
   public void initialize(ValidateEmail constraint) {
   }
   @Override
   public boolean isValid(String obj, ConstraintValidatorContext context) {
      Pattern pattern = Pattern.compile("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$");
      Matcher matcher = pattern.matcher(obj);
      return matcher.matches();
   }
}

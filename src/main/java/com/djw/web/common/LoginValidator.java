package com.djw.web.common;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {
  
  @Override
  public boolean supports(Class<?> clz) {
    return LoginModel.class.isAssignableFrom(clz);
  }
  
  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Cannot be empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Cannot be empty");
  }
}

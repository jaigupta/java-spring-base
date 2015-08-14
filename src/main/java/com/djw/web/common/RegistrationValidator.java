package com.djw.web.common;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {
  
  @Override
  public boolean supports(Class<?> clz) {
    return RegistrationModel.class.isAssignableFrom(clz);
  }
  
  @Override
  public void validate(Object target, Errors errors) {
    RegistrationModel model = (RegistrationModel) target;
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Cannot be empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Cannot be empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Cannot be empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Cannot be empty");
    if(!model.getPassword().equals(model.getPasswordAgain())) {
      errors.reject("passwordAgain", "Must be same as password!");
    }
  }
}

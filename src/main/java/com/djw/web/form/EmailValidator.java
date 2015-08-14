package com.djw.web.form;

import com.djw.web.common.WebResponse;

public class EmailValidator extends StringValidator {

  public EmailValidator(String data, WebResponse response, String label) {
    super(data, response, label);
  }

  public void validateEmail() {
    if (!data.matches("^[a-zA-z_][a-zA-Z_0-9]*@[a-zA-Z0-9_-]+\\.[a-zA-Z0-9]+$")) {
      response.addFormError(label, "Not a valid email.");
    }
  }
}

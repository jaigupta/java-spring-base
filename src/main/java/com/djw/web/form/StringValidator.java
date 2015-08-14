package com.djw.web.form;

import com.djw.web.common.WebResponse;

public class StringValidator {

  protected String data;
  protected WebResponse response;
  protected String label;

  public StringValidator(String data, WebResponse response, String label) {
    this.data = data;
    this.response = response;
    this.label = label;
  }

  public StringValidator minLength(int size) {
    if (data.length() < size) {
      response.addFormError(label, "Must have more than " + size + " chars.");
    }
    return this;
  }

  public StringValidator maxLength(int size) {
    if (data.length() > size) {
      response.addFormError(label, "Can have maximum of " + size + " chars.");
    }
    return this;
  }

  public StringValidator length(int minSize, int maxSize) {
    if (data.length() > maxSize || data.length() < minSize) {
      response.addFormError(label, "Length should be between " + minSize + " and " + maxSize);
    }
    return this;
  }
}

package com.djw.web.common;

public class RegistrationModel {
  private String username;
  private String password;
  private String passwordAgain;
  private String firstName;
  private String lastName;
  private String sex;
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getPasswordAgain() {
    return passwordAgain;
  }
  public void setPasswordAgain(String passwordAgain) {
    this.passwordAgain = passwordAgain;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
}

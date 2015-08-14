package com.djw.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.testng.util.Strings;

@Entity
@Table(name="user_profile")
public class UserProfile implements Serializable{
  
  @Id
  @Column(name="auth_id")
  private long authId;
  
  @Column(name="first_name")
  private String firstName;
  
  @Column(name="last_name")
  private String lastName;
  
  @Column(name="sex")
  private String sex;
  
  private static final long serialVersionUID = 1L;
  
  public String getFirstName() {
    return firstName;
  }
  public UserProfile setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }
  public String getLastName() {
    return lastName;
  }
  public UserProfile setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }
  public String getSex() {
    return sex;
  }
  public UserProfile setSex(String sex) {
    this.sex = sex;
    return this;
  }
  public long getAuthId() {
    return authId;
  }
  public UserProfile setAuthId(long authId) {
    this.authId = authId;
    return this;
  }

  public boolean isAuthenticated() {
    return this.authId != User.ANONYMOUS_ID;
  }

  public String getFullName() {
    return Strings.isNullOrEmpty(this.getLastName())
        ? this.getFirstName()
        : this.getFirstName() + " " + this.getLastName();
  }

  public static  UserProfile createAnonymousUserProfile() {
    return new UserProfile()
        .setAuthId(User.ANONYMOUS_ID)
        .setFirstName("Guest")
        .setLastName("")
        .setSex("M");
  }
}

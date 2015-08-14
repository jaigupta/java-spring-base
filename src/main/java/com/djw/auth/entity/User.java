package com.djw.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

  public static final long ANONYMOUS_ID = -1;
  public static final String ANONYMOUS_NAME = "Anonymous";

  private static final long serialVersionUID = 42L;

  @Id
  @GeneratedValue
  @Column(name = "auth_id")
  private long authId;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "enabled")
  private boolean enabled;

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

  public long getAuthId() {
    return authId;
  }

  public void setAuthId(long authId) {
    this.authId = authId;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}

package com.djw.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Role {
    ROLE_USER(1),
    ROLE_ADMIN(2),
    ROLE_SUPER_ADMIN(3);
    
    private int role_value;

    Role(int value) {
    	this.role_value = value;
    }

    public int getValue() {
    	return this.role_value;
    }
	}

	@Id
	@GeneratedValue
	@Column(name="role_id")
	private long roldId;

	@Column(name="auth_id")
	private long authId;

	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private Role role;

	public long getRoldId() {
	  return roldId;
  }

	public long getAuthId() {
	  return authId;
  }

	public UserRole setAuthId(long authId) {
	  this.authId = authId;
	  return this;
  }

	public Role getRole() {
	  return role;
  }

	public UserRole setRole(Role role) {
	  this.role = role;
	  return this;
  }
}

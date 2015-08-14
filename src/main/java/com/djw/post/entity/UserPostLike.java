package com.djw.post.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.djw.membership.entity.Membership;


@Entity
@Table(name="post_like")
public class UserPostLike extends Membership {
	@Column
	private String userName;

	@Column
	private int value;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
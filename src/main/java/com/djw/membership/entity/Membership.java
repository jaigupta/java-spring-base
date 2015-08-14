package com.djw.membership.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Membership {

	// Parent Entity
	@Column(name = "parent_id")
	private long parentId;

	// Child Entity
	@Column(name = "child_id")
	private long childId;
	
	@Id
  @GeneratedValue(strategy=GenerationType.TABLE)
	@Column
	private long id;

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public long getChildId() {
		return childId;
	}

	public void setChildId(long childId) {
		this.childId = childId;
	}
}

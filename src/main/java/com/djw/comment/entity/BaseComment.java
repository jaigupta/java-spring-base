package com.djw.comment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.djw.auth.entity.User;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseComment {

  public class State {
    // never delete or reorder states.
    // only add states at the end. This holds for any enum
    // as we deal with ordinals at the database end.
    public static final int COMMENT_DRAFT = 0;
    public static final int COMMENT_SUSPENDED = 1;
    public static final int COMMENT_ACTIVE = 2;
  }

  // comment id
  @Id
  @GeneratedValue(strategy=GenerationType.TABLE)
  @Column(name = "id")
  private long id;

  // -1 for unauthenticated posts
  @Column(name = "auth_id")
  private long authId;

  // Name to display with the post
  @Column(name = "name")
  private String name;

  // Comment data
  @Column(name = "data")
  private String data;

  // state of the comment
  @Column(name = "state")
  private int state;

  // Time of creation of post
  @Column(name = "time_created")
  private long timeCreated;

  @Column(name = "parent_id")
  private Long parentEntity;

  public BaseComment() {
  	this.authId = User.ANONYMOUS_ID;
  	this.data = "";
  	this.name = User.ANONYMOUS_NAME;
  	this.parentEntity = -1L;
  	this.state = State.COMMENT_ACTIVE;
  	this.timeCreated = new Date().getTime();
  }
  public long getAuthId() {
    return authId;
  }

  public String getData() {
    return data;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public long getParentEntityId() {
    return parentEntity;
  }

  public int getState() {
    return state;
  }

  public long getTimeCreated() {
    return timeCreated;
  }

  public BaseComment setAuthId(long authId) {
    this.authId = authId;
    return this;
  }

  public BaseComment setData(String data) {
    this.data = data;
    return this;
  }

  public BaseComment setId(long id) {
    this.id = id;
    return this;
  }

  public BaseComment setName(String name) {
    this.name = name;
    return this;
  }

  public BaseComment setParentEntityId(long parentEntityId) {
    this.parentEntity = parentEntityId;
    return this;
  }

  public BaseComment setState(int state) {
    this.state = state;
    return this;
  }

  public BaseComment setTimeCreated(long timeCreated) {
    this.timeCreated = timeCreated;
    return this;
  }
 
  public boolean isAuthenticatedComment() {
  	return this.authId != User.ANONYMOUS_ID;
  }
}

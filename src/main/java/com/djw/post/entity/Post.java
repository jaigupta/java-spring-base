package com.djw.post.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.djw.auth.entity.User;
import com.djw.entity.IEntity.EntityType;
import com.djw.indexing.solr.entity.IndexableDocument;

@Entity
@Table(name = "post")
public class Post {
	public static final int UNINITIALIZED_ID = -1;
	// post id
	@Id
	@Column
	@GeneratedValue
	private long id;

	// -1 for unauthenticated posts
	@Column
	private long authId;

	// Name to display with the post
	@Column(name = "name")
	private String name;

	@Column(name = "data")
	private String data;

	@Column
	private String title;

	// Optional path
	@Column(name = "path")
	private String path;

	// is the post a draft
	@Column
	private Boolean isDraft;

	// Is the post suspended
	@Column
	private Boolean suspended;

	// Time of creation of post
	@Column
	private long timeCreated;

	// Time of last edit of post
	@Column
	private long lastEdit;

	// Upvote count
	@Column
	private int upvoteCount;

	// Downvote count
	@Column
	private int downvoteCount;

	public Post() {
		id = UNINITIALIZED_ID;
		authId = -1;
		name = "";
		data = "";
		title = "";
		path = "";
		isDraft = false;
		suspended = false;
		Date date = new Date();
		timeCreated = date.getTime();
		lastEdit = date.getTime();
		setUpvoteCount(0);
		setDownvoteCount(0);
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

	public Boolean getIsDraft() {
		return isDraft;
	}

	public long getLastEdit() {
		return lastEdit;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public Boolean getSuspended() {
		return suspended;
	}

	public long getTimeCreated() {
		return timeCreated;
	}

	public String getTitle() {
		return title;
	}

	public Post setAuthId(long authId) {
		this.authId = authId;
		return this;
	}

	public Post setData(String data) {
		this.data = data;
		return this;
	}

	public Post setId(long id) {
		this.id = id;
		return this;
	}

	public Post setIsDraft(Boolean isDraft) {
		this.isDraft = isDraft;
		return this;
	}

	public Post setLastEdit(long lastEdit) {
		this.lastEdit = lastEdit;
		return this;
	}

	public Post setName(String name) {
		this.name = name;
		return this;
	}

	public Post setPath(String path) {
		this.path = path;
		return this;
	}

	public Post setSuspended(Boolean suspended) {
		this.suspended = suspended;
		return this;
	}

	public Post setTimeCreated(long timeCreated) {
		this.timeCreated = timeCreated;
		return this;
	}

	public Post setTitle(String title) {
		this.title = title;
		return this;
	}

	public boolean isAuthenticatedPost() {
		return authId != User.ANONYMOUS_ID;
	}

	public IndexableDocument toIndexableDocument() {
		if (this.id == UNINITIALIZED_ID) {
			throw new IllegalStateException(
			    "Post objects with no id cannot be converted to indexable docs.");
		}
		IndexableDocument doc = IndexableDocument
		    .getBuilder(EntityType.ENTITY_POST, this.id, this.title)
		    .description(this.data).build();
		return doc;
	}

	public int getUpvoteCount() {
		return upvoteCount;
	}

	public void setUpvoteCount(int upvoteCount) {
		this.upvoteCount = upvoteCount;
	}

	public int getDownvoteCount() {
		return downvoteCount;
	}

	public void setDownvoteCount(int downvoteCount) {
		this.downvoteCount = downvoteCount;
	}
}

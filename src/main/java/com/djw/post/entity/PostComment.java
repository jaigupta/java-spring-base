package com.djw.post.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.djw.comment.entity.BaseComment;

@Entity
@Table(name="post_comment")
public class PostComment extends BaseComment {
}

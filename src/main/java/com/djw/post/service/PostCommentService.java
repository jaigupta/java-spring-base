package com.djw.post.service;

import com.djw.database.NoSuchEntityException;
import com.djw.post.entity.PostComment;

public interface PostCommentService {
  public void addAuthorisedComment(PostComment comment);

  public void addUnauthorisedComment(PostComment comment);

  public Iterable<PostComment> getCommentsOnPost(long postId) throws NoSuchEntityException;

  public Iterable<PostComment> getCommentsByUser(long authId);
}

package com.djw.post.service;

import com.djw.post.entity.UserPostLike;

public interface UserPostLikeService {
	public void addLike(UserPostLike like);
	public void removeLike(UserPostLike like);
	public void updateLike(UserPostLike like);
	public Iterable<UserPostLike> getPosts(long userId);
	public Iterable<UserPostLike> getUsers(long postId);
	public boolean likesPost(long postId, long userId);
	public UserPostLike getLike(long postId, long userId);
}

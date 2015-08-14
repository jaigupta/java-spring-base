package com.djw.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djw.post.dao.UserPostLikeRepository;
import com.djw.post.entity.QUserPostLike;
import com.djw.post.entity.UserPostLike;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Transactional
public class UserPostLikeServiceImpl implements UserPostLikeService{

	@Autowired
	private UserPostLikeRepository userPostLikeDAO;

	@Override
	public void addLike(UserPostLike like) {
		userPostLikeDAO.save(like);

	}

	@Override
	public void removeLike(UserPostLike like) {
		userPostLikeDAO.delete(like);
	}

	@Override
	public void updateLike(UserPostLike like) {
		userPostLikeDAO.save(like);
	}

	@Override
	public Iterable<UserPostLike> getPosts(long userId) {
		QUserPostLike qUserPostLike = QUserPostLike.userPostLike;
		BooleanExpression getPosts = qUserPostLike.childId.eq(userId);
		return userPostLikeDAO.findAll(getPosts);
	}

	@Override
	public Iterable<UserPostLike> getUsers(long postId) {
		QUserPostLike qUserPostLike = QUserPostLike.userPostLike;
		BooleanExpression getUsers = qUserPostLike.parentId.eq(postId);
		return userPostLikeDAO.findAll(getUsers);
	}

	@Override
	public boolean likesPost(long postId, long userId) {
		QUserPostLike qUserPostLike = QUserPostLike.userPostLike;
		BooleanExpression getLike = qUserPostLike.parentId.eq(postId)
				.and(qUserPostLike.childId.eq(userId));
		return userPostLikeDAO.count(getLike) > 0;
	}

	@Override
	public UserPostLike getLike(long postId, long userId) {
		QUserPostLike qUserPostLike = QUserPostLike.userPostLike;
		BooleanExpression getLike = qUserPostLike.parentId.eq(postId)
				.and(qUserPostLike.childId.eq(userId));
		return userPostLikeDAO.findOne(getLike);
	}
}

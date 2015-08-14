package com.djw.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djw.auth.entity.User;
import com.djw.database.NoSuchEntityException;
import com.djw.post.dao.PostCommentRepository;
import com.djw.post.entity.PostComment;
import com.djw.post.entity.QPostComment;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Transactional
public class PostCommentServiceImpl implements PostCommentService {

	private final PostCommentRepository commentDAO;

	@Autowired
	public PostCommentServiceImpl(PostCommentRepository commentDAO) {
		this.commentDAO = commentDAO;
	}

	@Override
	public void addAuthorisedComment(PostComment comment) {
		commentDAO.save(comment);
	}

	@Override
	public void addUnauthorisedComment(PostComment comment) {
		comment.setAuthId(User.ANONYMOUS_ID);
		comment.setName(User.ANONYMOUS_NAME);
		commentDAO.save(comment);
	}

	@Override
	public Iterable<PostComment> getCommentsOnPost(long parentEntityId) throws NoSuchEntityException {
		QPostComment qPostComment = QPostComment.postComment;
		BooleanExpression postCommentsByPostId =
				qPostComment.parentEntity.eq(parentEntityId);
		return commentDAO.findAll(postCommentsByPostId);
	}

	@Override
	public Iterable<PostComment> getCommentsByUser(long authId) {
		QPostComment qPostComment = QPostComment.postComment;
		BooleanExpression postCommentsByUser = qPostComment.authId.eq(authId);
		return commentDAO.findAll(postCommentsByUser);
	}
}

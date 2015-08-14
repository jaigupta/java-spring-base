package com.djw.comment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.djw.comment.entity.BaseComment;

public  interface CommentRepository<T extends BaseComment> extends
		JpaRepository<T, Long>, QueryDslPredicateExecutor<T>{}

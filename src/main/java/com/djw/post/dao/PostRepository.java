package com.djw.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.djw.post.entity.Post;

public interface PostRepository extends
		JpaRepository<Post, Long>, QueryDslPredicateExecutor<Post>{}

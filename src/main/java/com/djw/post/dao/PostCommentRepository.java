package com.djw.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.djw.post.entity.PostComment;

public  interface PostCommentRepository extends JpaRepository<PostComment, Long>,
QueryDslPredicateExecutor<PostComment>{}

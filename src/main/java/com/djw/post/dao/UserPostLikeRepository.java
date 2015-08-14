package com.djw.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.djw.post.entity.UserPostLike;

public interface UserPostLikeRepository extends
JpaRepository<UserPostLike, Long>, QueryDslPredicateExecutor<UserPostLike>{}

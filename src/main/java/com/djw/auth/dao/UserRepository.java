package com.djw.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.djw.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long>,
    QueryDslPredicateExecutor<User> {
}

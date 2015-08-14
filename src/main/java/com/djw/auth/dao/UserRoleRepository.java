package com.djw.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.djw.auth.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>,
    QueryDslPredicateExecutor<UserRole> {
}

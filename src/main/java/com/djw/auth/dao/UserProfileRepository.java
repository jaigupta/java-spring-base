package com.djw.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.djw.auth.entity.UserProfile;

public interface UserProfileRepository extends
    JpaRepository<UserProfile, Long>, QueryDslPredicateExecutor<UserProfile> {
}

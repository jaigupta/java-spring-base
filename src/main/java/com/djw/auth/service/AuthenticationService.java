package com.djw.auth.service;

import com.djw.auth.entity.User;
import com.djw.auth.entity.UserProfile;
import com.djw.database.EntityExistsException;
import com.djw.database.NoSuchEntityException;

public interface AuthenticationService {
  public User authenticate(String username, String password)
      throws AuthenticationException;

  public void createUser(User user)
      throws EntityExistsException;

  public void deleteUser(String username) throws NoSuchEntityException;

  public User getUser(String username) throws NoSuchEntityException;

  public UserProfile getUserProfile(long l) throws NoSuchEntityException;
}
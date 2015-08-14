package com.djw.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djw.auth.dao.UserProfileRepository;
import com.djw.auth.dao.UserRepository;
import com.djw.auth.entity.QUser;
import com.djw.auth.entity.User;
import com.djw.auth.entity.UserProfile;
import com.djw.database.EntityExistsException;
import com.djw.database.NoSuchEntityException;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserRepository userDAO;
  private final UserProfileRepository userProfileDAO;

  @Autowired
  public AuthenticationServiceImpl(UserRepository userDAO,
      UserProfileRepository userProfileDAO) {
    this.userDAO = userDAO;
    this.userProfileDAO = userProfileDAO;
  }

  @Override
  @Transactional
  public User authenticate(String username, String password)
      throws AuthenticationException {
    QUser quser = QUser.user;
    BooleanExpression userByCredentials = quser.username.eq(username).and(
        quser.password.eq(password));
    User user = userDAO.findOne(userByCredentials);
    if (user == null) {
      throw new AuthenticationException("Failed to Authenticate User: "
          + username);
    }
    return user;
  }

  @Override
  @Transactional
  public void createUser(User user) throws EntityExistsException {
    QUser quser = QUser.user;
    BooleanExpression userByUsername = quser.username.eq(user.getUsername());
    if (userDAO.findOne(userByUsername) != null) {
      throw new EntityExistsException("User: " + user.getUsername()
          + " already exists");
    } else {
      userDAO.save(user);
    }
  }

  @Override
  @Transactional
  public void deleteUser(String username) throws NoSuchEntityException {
    User user = getUser(username);
    userDAO.delete(user.getAuthId());
  }

  @Override
  @Transactional
  public User getUser(String username) throws NoSuchEntityException {
    BooleanExpression userByUsername = QUser.user.username.eq(username);
    User user = userDAO.findOne(userByUsername);
    if (user == null) {
      throw new NoSuchEntityException("User does not exist: " + username);
    }
    return user;
  }

  @Override
  @Transactional
  public UserProfile getUserProfile(long authId) throws NoSuchEntityException {
    UserProfile userProfile = userProfileDAO.findOne(authId);
    if (userProfile == null) {
      throw new NoSuchEntityException(
          "Could not find associated UserProfile for id: " + authId);
    }
    return userProfile;
  }
}
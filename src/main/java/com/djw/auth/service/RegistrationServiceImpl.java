package com.djw.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djw.auth.dao.UserProfileRepository;
import com.djw.auth.dao.UserRoleRepository;
import com.djw.auth.entity.User;
import com.djw.auth.entity.UserProfile;
import com.djw.auth.entity.UserRole;
import com.djw.auth.entity.UserRole.Role;
import com.djw.database.EntityExistsException;
import com.djw.database.NoSuchEntityException;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
  private final AuthenticationService authClient;
  private final UserProfileRepository userProfileDAO;
  private final UserRoleRepository userRoleDAO;

  @Autowired
  public RegistrationServiceImpl(AuthenticationService authClient,
      UserProfileRepository userProfileDAO,
      UserRoleRepository userRoleDAO) {
    this.authClient = authClient;
    this.userProfileDAO = userProfileDAO;
    this.userRoleDAO = userRoleDAO;
  }

  @Override
  @Transactional
  public void deleteUser(String username) {
    // TODO(jaigupta): Auto-generated method stub

  }

  @Override
  @Transactional
  public void register(User user, UserProfile userProfile) throws EntityExistsException {
  	register(user, userProfile, UserRole.Role.ROLE_USER);
  }

  @Override
  @Transactional
  public void register(User user, UserProfile userProfile, Role userRole)
      throws EntityExistsException {
    try {
      // Check if user exists. Should throw exception.
      authClient.getUser(user.getUsername());
      // This
      throw new EntityExistsException("User :" + user.getUsername()
          + " already exists!");
    } catch (NoSuchEntityException ex) {
      // Expected here.
    }
    authClient.createUser(user);
    User createdUser = null;
    try {
      createdUser = authClient.getUser(user.getUsername());
    } catch (NoSuchEntityException ex) {
      // TODO(jaigupta): PermanentFailure
    }
    UserRole role = new UserRole().setAuthId(
    		createdUser.getAuthId()).setRole(userRole);
    userRoleDAO.save(role);
    userProfile.setAuthId(createdUser.getAuthId());
    userProfileDAO.save(userProfile);
  }

  @Override
  @Transactional
  public void updateUserProfile(UserProfile userProfile) {
  	userProfileDAO.save(userProfile);
  }
}

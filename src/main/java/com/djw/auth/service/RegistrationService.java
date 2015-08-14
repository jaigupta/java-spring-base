package com.djw.auth.service;

import com.djw.auth.entity.User;
import com.djw.auth.entity.UserProfile;
import com.djw.auth.entity.UserRole.Role;
import com.djw.database.EntityExistsException;

public interface RegistrationService {
  public void register(User user, UserProfile userProfile, Role userRole) 
      throws EntityExistsException;
  public void register(User user, UserProfile userProfile) 
      throws EntityExistsException;
  public void deleteUser(String username);
	void updateUserProfile(UserProfile userProfile);
}

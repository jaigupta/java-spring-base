package com.djw.web.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.djw.auth.entity.User;
import com.djw.auth.entity.UserProfile;
import com.djw.auth.service.AuthenticationService;
import com.djw.database.NoSuchEntityException;
import com.djw.web.constant.SessionConstants;

@Component
public class LoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  private final Logger logger = Logger.getLogger(LoginHandler.class.getName());
  private final AuthenticationService authService;

  @Autowired
  LoginHandler(AuthenticationService authService) {
    super();
    this.authService = authService;
    this.setUseReferer(true);
    this.setDefaultTargetUrl("/home");
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
      HttpServletResponse response, Authentication authentication)
      throws IOException {
    // TODO(jaigupta): Setup user roles
    // get the user authorities(roles)
    // AuthorityUtils.authorityListToSet(authentication.getAuthorities());
    String username = authentication.getName();
    User user = null;
    try {
      user = authService.getUser(username);
    } catch (NoSuchEntityException e) {
      logger.warn("Logged in user does not exist!", e);
      authentication.setAuthenticated(false);
    }
    UserProfile userProfile = null;
    try {
        userProfile = authService.getUserProfile(user.getAuthId());
    } catch (NoSuchEntityException e) {
        logger.warn("Authenticated user has no associated profile!", e);
        authentication.setAuthenticated(false);
        userProfile = UserProfile.createAnonymousUserProfile();
    }
    request.getSession().setAttribute(SessionConstants.AUTH_ID, user.getAuthId());
    request.getSession().setAttribute(SessionConstants.USERNAME, user.getUsername());
    request.getSession().setAttribute(SessionConstants.FIRST_NAME, userProfile.getFirstName());
    request.getSession().setAttribute(SessionConstants.LAST_NAME, userProfile.getLastName());
    request.getSession().setAttribute(SessionConstants.NAME,
            userProfile.getFirstName() + " " + userProfile.getLastName());
    request.getSession().setAttribute(SessionConstants.IS_AUTHENTICATED,  userProfile.isAuthenticated());
    response.sendRedirect(this.getDefaultTargetUrl());
  }
}
package com.djw.auth.service;

import static junit.framework.Assert.assertEquals;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.djw.auth.entity.User;
import com.djw.auth.entity.UserProfile;
import com.djw.database.EntityExistsException;
import com.djw.database.NoSuchEntityException;
import com.djw.testing.Config;

@Test
@ContextConfiguration(locations = { Config.SPRING_BEAN })
public class RegistrationServiceImplTest extends
    AbstractTestNGSpringContextTests {

  private final static String USERNAME = "user";
  private final static String PASSWORD = "password";
  private final static String FIRST_NAME = "firstName";
  private final static String LAST_NAME = "lastName";
  private final static String SEX = "M";
  private final RegistrationService registrationService;
  private final AuthenticationService authService;
  private final ApplicationContext context;

  public RegistrationServiceImplTest() {
    context = new ClassPathXmlApplicationContext(Config.SPRING_BEAN);
    this.registrationService = context.getBean(RegistrationService.class);
    this.authService = context.getBean(AuthenticationService.class);
  }

  @Test()
  void testAll() throws EntityExistsException, AuthenticationException,
      NoSuchEntityException {
    // Create user object
    User user = new User();
    user.setUsername(USERNAME);
    user.setPassword(PASSWORD);
    // Create profile for the user
    UserProfile userProfile = new UserProfile();
    userProfile.setFirstName(FIRST_NAME);
    userProfile.setLastName(LAST_NAME);
    userProfile.setSex(SEX);
    // Now register the user
    registrationService.register(user, userProfile);
    User retrievedUser = authService.authenticate(USERNAME, PASSWORD);
    UserProfile retrievedProfile = authService.getUserProfile(retrievedUser
        .getAuthId());
    assertEquals(userProfile.getAuthId(), retrievedProfile.getAuthId());
    assertEquals(userProfile.getFirstName(), retrievedProfile.getFirstName());
    assertEquals(userProfile.getLastName(), retrievedProfile.getLastName());
    assertEquals(userProfile.getSex(), retrievedProfile.getSex());
  }
}

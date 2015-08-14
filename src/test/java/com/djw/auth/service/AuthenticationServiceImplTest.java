package com.djw.auth.service;

import static junit.framework.Assert.fail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.djw.auth.entity.User;
import com.djw.database.EntityExistsException;
import com.djw.testing.Config;

@Test
@ContextConfiguration(locations = { Config.SPRING_BEAN })
public class AuthenticationServiceImplTest extends AbstractTestNGSpringContextTests {

  private final static String USERNAME = "user";
  private final static String PASSWORD = "password";
  private final AuthenticationService authService;
  private final ApplicationContext context;

  public AuthenticationServiceImplTest() {
    context = new ClassPathXmlApplicationContext("classpath:Beans.xml");
    this.authService = context.getBean(AuthenticationService.class);
  }

  
  @Test()
  void testAll() throws EntityExistsException, AuthenticationException {
    // Test User creation succeeds
  	User user = new User();
  	user.setUsername("username");
  	user.setPassword("password");
  	user.setEnabled(true);
    authService.createUser(user);
    try {
      authService.createUser(user);
      fail("User should already exist!");
    } catch (EntityExistsException e) {
      // Expected
    }
    authService.authenticate(USERNAME, PASSWORD);
    try {
      authService.authenticate(USERNAME, "");
      fail("Authentication passed with wrong password!");
    } catch (AuthenticationException e) {
      // Expected
    }
  }
}

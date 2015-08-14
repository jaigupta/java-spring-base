package com.djw.post.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.djw.auth.entity.User;
import com.djw.auth.entity.UserProfile;
import com.djw.auth.service.AuthenticationException;
import com.djw.auth.service.AuthenticationService;
import com.djw.auth.service.RegistrationService;
import com.djw.database.EntityExistsException;
import com.djw.database.NoSuchEntityException;
import com.djw.post.entity.Post;
import com.djw.testing.Config;

@Test
@ContextConfiguration(locations = { Config.SPRING_BEAN })
public class PostServiceImplTest extends AbstractTestNGSpringContextTests {
  private final static String USERNAME = "user";
  private final static String PASSWORD = "password";
  private final static String FIRST_NAME = "firstName";
  private final static String LAST_NAME = "lastName";
  private final static String SEX = "M";
  private final RegistrationService registrationService;
  private final AuthenticationService authService;
  private final PostService postService;
  private final ApplicationContext context;

  PostServiceImplTest() {
    context = new ClassPathXmlApplicationContext(Config.SPRING_BEAN);
    this.registrationService = context.getBean(RegistrationService.class);
    this.authService = context.getBean(AuthenticationService.class);
    this.postService = context.getBean(PostService.class);
  }

  private boolean containsAnonymousPost(List<Post> postList) {
    for (Post post : postList) {
      if (post.getAuthId() == -1) {
        return true;
      }
    }
    return false;
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
    user = authService.authenticate(USERNAME, PASSWORD);
    userProfile = authService.getUserProfile(user.getAuthId());
    /*
    postService.createAuthenticatedPost(user.getAuthId(),
        userProfile.getFirstName(), "title", "data", "path", false, true);
    postService.createUnauthenticatedPost("Anonymous", "title1", "data1",
        "path1", false, true);
    List<Post> userPosts = postService.getUserPosts(user.getAuthId());
    assertEquals(1, userPosts.size());
    assertEquals(user.getAuthId(), userPosts.get(0).getAuthId());
    List<Post> titlePosts = postService.getPostFromTitle("title");
    assertEquals(2, titlePosts.size());
    assertTrue(containsAnonymousPost(titlePosts));
    */
  }
}

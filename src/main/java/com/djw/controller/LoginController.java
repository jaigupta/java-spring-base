package com.djw.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djw.auth.entity.User;
import com.djw.auth.entity.UserProfile;
import com.djw.auth.service.AuthenticationException;
import com.djw.auth.service.AuthenticationService;
import com.djw.database.NoSuchEntityException;
import com.djw.web.common.WebResponse;

@Controller
@RequestMapping("login")
public class LoginController {
  private static final Logger logger = Logger.getLogger(LoginController.class);
  private final AuthenticationService auth;

  @Autowired
  public LoginController(AuthenticationService auth) {
    this.auth = auth;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String initForm(WebResponse response) {
    response.setTargetPage("login");
    return "landing";
  }

  @RequestMapping(value = "/json", method = RequestMethod.POST)
  @ResponseBody
  public String processSubmit(@RequestParam("username") String username,
      @RequestParam("password") String password, WebResponse response,
      HttpServletRequest request) throws JsonGenerationException,
      JsonMappingException, IOException {
    loginUser(response, username, password, request);
    return response.getJsonResponse();
  }

  @RequestMapping(value = "/invalidate")
  public String logout(HttpServletRequest request,
      HttpServletResponse servletResponse, WebResponse response) {
    new SecurityContextLogoutHandler().logout(request, servletResponse, null);
    request.getSession().invalidate();
    response.setTargetPage("login");
    response.getPreparedResponse();
    return "landing";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String login(@RequestParam("username") String username,
      @RequestParam("password") String password, WebResponse response,
      HttpServletRequest request) {
    loginUser(response, username, password, request);
    if (response.hasError()) {
      response.setTargetPage("login");
      response.getPreparedResponse();
      return "landing";
    }
    return "redirect:/user/me";
  }

  private void loginUser(WebResponse response, String username,
      String password, HttpServletRequest request) {
    if (response.hasError()) {
      // Do not try to login if form has initial errors
      return;
    }
    try {
      User user = auth.authenticate(username, password);
      request.getSession().setAttribute("authId", user.getAuthId());
      request.getSession().setAttribute("username", user.getUsername());
      UserProfile userProfile = auth.getUserProfile(user.getAuthId());
      request.getSession().setAttribute("firstName", userProfile.getFirstName());
      request.getSession().setAttribute("lastName", userProfile.getLastName());
      request.getSession().setAttribute(
          "name", userProfile.getFirstName() + " " + userProfile.getLastName());
      request.getSession().setAttribute("isAuthenticated", true);
      response.setSuccess();
    } catch (AuthenticationException e) {
      request.getSession().invalidate();
      response.addError(e.getMessage());
    } catch (NoSuchEntityException e) {
      logger.warn("No profile associated with user!");
      e.printStackTrace();
    }
    response.getPreparedResponse();
    return;

  }
  // TODO: remember what are @ModelAttribute and initBinder
}

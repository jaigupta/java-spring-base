package com.djw.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.djw.auth.entity.User;
import com.djw.auth.entity.UserProfile;
import com.djw.auth.service.RegistrationService;
import com.djw.database.EntityExistsException;
import com.djw.web.common.WebResponse;
import com.djw.web.form.EmailValidator;
import com.djw.web.form.StringValidator;

@Controller
@RequestMapping("register")
public class RegisterController {

  private final RegistrationService registrationClient;

  @Autowired
  public RegisterController(RegistrationService registrationClient) {
    this.registrationClient = registrationClient;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String initForm(WebResponse response, HttpServletRequest request) {
  	response.disableSidebar();
  	response.setTargetPage("register");
    return "landing";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String processSubmit(HttpServletRequest request,
      @RequestParam("firstname") String firstName,
      @RequestParam("lastname") String lastName,
      @RequestParam("sex") String sex,
      @RequestParam("username") String username,
      @RequestParam("password") String password,
      @RequestParam("passwordagain") String passwordAgain, WebResponse response) {
    response.addAttribute("magic", "mmmm");
    new StringValidator(firstName, response, "firstname").length(1, 20);
    new StringValidator(lastName, response, "lastname").length(1, 20);
    new StringValidator(password, response, "password").length(5, 20);
    new EmailValidator(username, response, "username").validateEmail();
    if (!password.equals(passwordAgain)) {
      response.addFormError("passwordagain", "Passwords mismatch!" + password + "/" + passwordAgain);
    }
    if (response.hasError()) {
    	response.disableSidebar();
      response.getPreparedResponse();
      response.setTargetPage("register");
      return "landing";
    }
    try {
      User user = new User();
      user.setUsername(username);

      user.setPassword(password);
      user.setEnabled(true);
      UserProfile userProfile = new UserProfile();
      userProfile.setFirstName(firstName);
      userProfile.setLastName(lastName);
      userProfile.setSex(sex);
      request.getSession().setAttribute("name", user.getUsername());
      registrationClient.register(user, userProfile);
      response.setSuccess();
      response.addInfo("User successfully created!");
      response.addAttribute("username", user.getUsername());
      response.addAttribute("user_profile", userProfile);
    } catch (EntityExistsException e) {
    	response.addFormError("username", "Sorry! This username has already been taken");
      response.addError(e.getMessage());
      response.getPreparedResponse();
      response.setTargetPage("register");
      response.disableSidebar();
      return "landing";
    }
    response.disableSidebar();
    response.setTargetPage("login");
    return "landing";
  }
}

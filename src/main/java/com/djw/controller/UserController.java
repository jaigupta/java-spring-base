package com.djw.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djw.auth.entity.UserProfile;
import com.djw.auth.service.AuthenticationService;
import com.djw.auth.service.RegistrationService;
import com.djw.database.NoSuchEntityException;
import com.djw.web.common.WebResponse;
import com.djw.web.producer.SessionEntity;
import com.google.common.base.Preconditions;

@Controller
@RequestMapping("user")
public class UserController {

  Logger logger = Logger.getLogger(UserController.class.getName());

  @Autowired
  AuthenticationService authService;
  @Autowired
  RegistrationService regService;

  @RequestMapping(value = "/me", method = RequestMethod.GET)
  public String getMe(WebResponse response,
      @SessionEntity UserProfile requesterUser) {
    long authId = requesterUser.getAuthId();
    fillUserProfileFromId(authId, response);
    response.setTargetPage("profile");
    response.getPreparedResponse();
//    Preconditions.checkNotNull(SecurityContextHolder.getContext());
//    Preconditions.checkNotNull(SecurityContextHolder.getContext().getAuthentication());
//    Preconditions.checkNotNull(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
//    Collection<SimpleGrantedAuthority> authorities =
//        (Collection<SimpleGrantedAuthority>) SecurityContextHolder
//            .getContext().getAuthentication().getAuthorities();
//    for(SimpleGrantedAuthority authority : authorities) {
//      logger.log(Level.INFO, authority.getAuthority());
//    }
    return "landing";
  }

  @ResponseBody
  @RequestMapping(value = "/json/id/{id}")
  public String getUserJson(@PathVariable long id, WebResponse response)
      throws JsonGenerationException, JsonMappingException, IOException {
    fillUserProfileFromId(id, response);
    return response.getJsonResponse();
  }

  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public String getUser(@PathVariable long id, WebResponse response) {
    fillUserProfileFromId(id, response);
    response.setTargetPage("profile");
    response.getPreparedResponse();
    return "landing";
  }

  @RequestMapping(value = "/edit")
  public String editProfile(
      @RequestParam(value = "firstname", defaultValue = "") String firstName,
      @RequestParam(value = "lastname", defaultValue = "") String lastName,
      @RequestParam(value = "sex", defaultValue = "") String sex,
      @SessionEntity UserProfile requesterProfile,
      WebResponse response)
      throws NoSuchEntityException {
    if (firstName.equals("")) {
      firstName = requesterProfile.getFirstName();
    }
    if (lastName.equals("")) {
      lastName = requesterProfile.getLastName();
    }
    if (sex.equals("")) {
      sex = requesterProfile.getSex();
    }
    if (!firstName.equals(requesterProfile.getFirstName())
        || !lastName.equals(requesterProfile.getLastName())
        || !sex.equals(requesterProfile.getSex())) {
      requesterProfile.setFirstName(firstName);
      requesterProfile.setLastName(lastName);
      requesterProfile.setSex(sex);
      regService.updateUserProfile(requesterProfile);
    }
    response.addAttribute("firstname", firstName);
    response.addAttribute("lastname", lastName);
    response.addAttribute("sex", sex);
    response.setTargetPage("profile_edit");
    response.getPreparedResponse();
    return "landing";
  }

  private void fillUserProfileFromId(long userId, WebResponse response) {
    try {
      UserProfile userProfile = authService.getUserProfile(userId);
      response.addAttribute("profile", userProfile);
    } catch (NoSuchEntityException e) {
      response.addError(e.getMessage());
    }
  }
}
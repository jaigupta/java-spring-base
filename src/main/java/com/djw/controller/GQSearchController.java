package com.djw.controller;

import org.apache.log4j.Logger;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.djw.web.common.WebResponse;

@Controller
@RequestMapping(value= {"gqsearch"})
public class GQSearchController {
  static final Logger logger = Logger.getLogger(LoginController.class);

  @RequestMapping(method=RequestMethod.GET)
  public String getAbout(WebResponse response) {
      response.setTargetPage("gqsearch");
      response.getPreparedResponse();
      return "landing";
  }
}

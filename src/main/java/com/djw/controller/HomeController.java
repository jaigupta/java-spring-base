package com.djw.controller;

import org.apache.log4j.Logger;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value= {"/", "home"})
public class HomeController {
  static final Logger logger = Logger.getLogger(LoginController.class);

  @RequestMapping(method=RequestMethod.GET)
  public String initForm() {
    return "landing";
  }
}

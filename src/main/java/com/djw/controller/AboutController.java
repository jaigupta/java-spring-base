package com.djw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.djw.web.common.WebResponse;

@Controller
public class AboutController {
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String getAbout(WebResponse response) {
		response.setTargetPage("about");
		response.getPreparedResponse();
		return "landing";
	}
}
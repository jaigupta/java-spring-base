package com.djw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.djw.web.common.WebResponse;

@Controller
public class ContactController {
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contactUs(WebResponse response) {
		response.setTargetPage("contact");
		response.getPreparedResponse();
		return "landing";
	}
}
package com.djw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.djw.web.common.WebResponse;

@Controller
public class ServicesController {
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public String getService(WebResponse response) {
		response.setTargetPage("services");
		response.getPreparedResponse();
		return "landing";
	}
}
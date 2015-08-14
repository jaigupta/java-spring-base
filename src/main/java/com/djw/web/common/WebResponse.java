package com.djw.web.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class WebResponse {

  enum Status {
    SUCCESS, FAILURE, NONE,
  }

  private ModelMap _map;
  private ModelMap form;
  private Map<String, String> formError;
  private List<String> errorList;
  private List<String> infoList;
  private Status responseStatus = Status.NONE;
	private static ObjectMapper jsonMapper = new ObjectMapper();

  @Autowired
  public WebResponse(ModelMap map) {
    this._map = map;
    this.errorList = new ArrayList<String>();
    this.infoList = new ArrayList<String>();
    form = new ModelMap();
    this.formError = new HashMap<String, String>();
  }

  public void addAllErrors(BindingResult result) {
    for (ObjectError err : result.getAllErrors()) {
      errorList.add(err.getObjectName() + ":" + err.getDefaultMessage());
    }
  }

  public void addError(String msg) {
    errorList.add(msg);
  }

  public boolean hasError() {
    return this.responseStatus == Status.FAILURE;
  }

  public void addInfo(String msg) {
    infoList.add(msg);

  }

  private void addErrorsToResponse() {
    if (!errorList.isEmpty()) {
      _map.addAttribute("errors", errorList);
      this.setFailed();
    }
  }

  private void addInfosToResponse() {
    if (!infoList.isEmpty()) {
      _map.addAttribute("infos", infoList);
    }
  }

  private boolean addFormErrors() {
  	if (formError.size() == 0) return false;
    ModelMap error = new ModelMap();
    for (String key : formError.keySet()) {
      error.addAttribute(key, formError.get(key));
    }
    form.addAttribute("error", error);
    return true;
  }

  private void addFormFields() {
    if (!this.addFormErrors())
    	return;
    _map.addAttribute("form", form);
  }

  public ModelMap getPreparedResponse() {
    this.addInfosToResponse();
    this.addErrorsToResponse();
    this.addFormFields();
    if (this.responseStatus != Status.NONE) {
      _map.addAttribute("status", this.responseStatus.toString().toLowerCase());
    }
    return _map;
  }

  public String getJsonResponse()
  		throws JsonGenerationException, JsonMappingException, IOException {
  	getPreparedResponse();
  	return jsonMapper.writeValueAsString(_map);
  }

  public void setSuccess() {
    this.responseStatus = Status.SUCCESS;
  }

  public void setFailed() {
    this.responseStatus = Status.FAILURE;
  }

  public void setSuccessNoStatus() {
    if (this.responseStatus == Status.NONE) {
      this.responseStatus = Status.SUCCESS;
    }
  }

  public void addAttribute(String attributeName, Object attributeValue) {
    _map.addAttribute(attributeName, attributeValue);
  }

  public void addFormError(String elementName, String attributeValue) {
    String prevValue = formError.get(elementName);
    String newValue = (prevValue == null) ? attributeValue :
        (attributeValue + " " + prevValue);
    formError.put(elementName, newValue);
    setFailed();
  }

  public void addAllAttributes(Map<String, ?> attributes) {
    _map.addAllAttributes(attributes);
  }

  public void setTargetPage(String targetPage) {
	  _map.addAttribute("render_page", targetPage);
  }

  public void clear() {
    _map.clear();
  }

  public void disableSidebar() {
  	_map.addAttribute("view_sidebar_disable", false);
  }
}

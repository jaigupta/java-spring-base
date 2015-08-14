package com.djw.web.producer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.djw.auth.entity.UserProfile;
import com.djw.auth.service.AuthenticationService;
import com.djw.database.NoSuchEntityException;
import com.djw.web.common.WebResponse;
import com.djw.web.constant.SessionConstants;
import com.google.common.base.Preconditions;

@Component
public class SessionParamArgumentResolver implements
    HandlerMethodArgumentResolver {

  @Autowired AuthenticationService authService;

  private Map<ProducerNode, Method> producerMethodMap = new HashMap<>();
  private Map<Class<?>, Object> injectableParameters = new HashMap<>();

  @Override
  public Object resolveArgument(MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) {
    /**
    injectableParameters.put(MethodParameter.class, parameter);
    injectableParameters.put(ModelAndViewContainer.class, mavContainer);
    injectableParameters.put(NativeWebRequest.class, webRequest);
    injectableParameters.put(WebDataBinderFactory.class, binderFactory);
    setupAllSupportedBindings();
    */

    HttpServletRequest servletRequest = (HttpServletRequest) webRequest.getNativeRequest();
    HttpSession session = servletRequest.getSession();
    if (parameter.getParameterType().isAssignableFrom(WebResponse.class)) {
      return new WebResponse(mavContainer.getModel());
    }
    if(parameter.getParameterType().isAssignableFrom(UserProfile.class)) {
      return getUserProfile(session);
    }
    return null;
  }

  private void setupAllSupportedBindings() {
    
    // TODO(jaigupta): we are still not using this as this we can implement it better using
    // graph building of the dependcies.
    Class<?> classType = this.getClass();
    Method methods[] = classType.getDeclaredMethods();
    for (Method method : methods) {
      Annotation annotations [] = method.getAnnotations();
      Preconditions.checkState(annotations.length == 1,
          "Found more than one annotation for method %s", methods.toString());
      Annotation annotation = annotations[0];
      Class<?> returnType = method.getReturnType();
      do {
        producerMethodMap.put(new ProducerNode(returnType, annotation), method);
        for (Class<?> interfaceClass : returnType.getInterfaces()) {
          producerMethodMap.put(new ProducerNode(interfaceClass, annotation), method);
        }
        returnType = returnType.getSuperclass();
      } while (returnType.equals(Object.class));
    }
  }

  private UserProfile getUserProfile(HttpSession session) {
    Long authId = (Long) session.getAttribute(SessionConstants.AUTH_ID);
    if (authId == null) {
      return UserProfile.createAnonymousUserProfile();
    }
    try {
      return authService.getUserProfile(authId);
    } catch (NoSuchEntityException e) {
      // there is no profile with user provided
      // TODO(jaigupta): Take care this does not happen when the user deletes his account.
      e.printStackTrace();
      throw new RuntimeException("Not a valid state to come to!");
    }
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    boolean sessionEntityRequested = annotatedWithSessionEntity(parameter);
    if (parameter.getParameterType().isAssignableFrom(WebResponse.class)
        || (parameter.getParameterType().isAssignableFrom(UserProfile.class)
            && sessionEntityRequested)) {
      return true;
    }
    return false;
  }

  private boolean annotatedWithSessionEntity(MethodParameter parameter) {
    for (Annotation annotation : parameter.getParameterAnnotations()) {
      if (annotation.annotationType().equals(SessionEntity.class)) {
        return true;
      }
    }
    return false;
  }
}

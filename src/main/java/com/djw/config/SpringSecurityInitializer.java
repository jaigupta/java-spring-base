//package com.djw.config;
//
//import javax.servlet.ServletContext;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
//import org.springframework.stereotype.Component;
//
///**
// * This class should have been initialized at runtime. But as we are not able to load the configs
// * from web.xml with embedded servers(used by appengine) we are loading this in MyDispatcherConfig
// * using @Autowired annotation on method.
// */
//@Order(value = 2)
//@Component
//public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
//   // Do nothing. This initializes the security chain.
//  final static Logger logger = LoggerFactory.getLogger(SpringSecurityInitializer.class);
//  private static boolean isInitialized = false;
//
//  @Override
//  public void beforeSpringSecurityFilterChain(ServletContext servletContext) {
//    if (isInitialized) {
//      throw new RuntimeException("Initializing second time");
//    }
//    logger.warn("Starting to load spring security filter chain");
//  }
//
//  @Override
//  public void afterSpringSecurityFilterChain(ServletContext servletContext) {
//    logger.warn("Finished to load spring security filter chain");
//  }
//}

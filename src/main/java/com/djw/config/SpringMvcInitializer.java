/**
 * Disabling this class as we are using the web.xml configuration. if we manage to get embedded
 * jetty to work without web.xml file, we might migrate to this.
 */
//package com.djw.config;
//
//import org.springframework.core.annotation.Order;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//@Order(value = 1)
//public class SpringMvcInitializer 
//       extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] { SecurityConfig.class };
//    }
// 
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[] { MyDispatcherConfig.class };
//    }
// 
//    @Override
//    protected String[] getServletMappings() {
//        return new String[] { "/" };
//    }
//}
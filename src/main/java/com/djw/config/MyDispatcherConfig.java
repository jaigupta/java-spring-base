package com.djw.config;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.djw.web.producer.SessionParamArgumentResolver;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.djw.controller" })
@PropertySources(@PropertySource({
  "classpath:properties/application.properties",
  "classpath:properties/hibernate.properties"}))
//@Import(SecurityConfig.class)
public class MyDispatcherConfig extends WebMvcConfigurerAdapter {

  @Autowired SessionParamArgumentResolver sessionArgumentResolver;


  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/view/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }

  @Override
  public void addArgumentResolvers(
      List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(sessionArgumentResolver);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
    registry.addResourceHandler("/images/**").addResourceLocations("/images/");
  }

//  @Autowired
//  public void throwExp(SpringSecurityInitializer securityInitializer,
//      SecurityConfig securityConfig) throws ServletException {
//    // security configs must be loaded for this to work. hence injecting it though not using it
//    securityInitializer.onStartup(this.servletContext);
//  }
}

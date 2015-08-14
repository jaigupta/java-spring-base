package com.djw.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CentralModule {
  ApplicationContext context;
  public void initModules() {
    context = new ClassPathXmlApplicationContext("Beans.xml");
    
  }
}

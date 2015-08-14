package com.djw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.djw.config.Profiles.DevProfile;

@Configuration
@DevProfile
@PropertySources(@PropertySource("classpath:properties/dev/jdbc.properties"))
public class DevConfig {

  @Autowired
  private Environment env;

  @Bean
  public DriverManagerDataSource dataSource() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    driverManagerDataSource.setDriverClassName(env
        .getProperty("jdbc.driverClassName"));
    String username = env.getProperty("jdbc.username");
    String password = env.getProperty("jdbc.password");
    driverManagerDataSource.setUrl(env.getProperty("jdbc.url"));
    driverManagerDataSource.setUsername(username);
    driverManagerDataSource.setPassword(password);
    return driverManagerDataSource;
  }
}

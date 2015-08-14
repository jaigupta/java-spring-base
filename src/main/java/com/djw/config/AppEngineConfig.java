package com.djw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.djw.config.Profiles.AppEngineProfile;

@Configuration
@AppEngineProfile
@PropertySources(@PropertySource("classpath:properties/prod/jdbc.properties"))
public class AppEngineConfig {

  @Autowired
  Environment env;

  @Bean
  public DriverManagerDataSource dataSource() {
    if (env.getProperty("jdbc.server_type", String.class).equals("appengine")) {
        return getDriverManagerForGoogleCloudSql();
    }
    throw new RuntimeException("This is error! We dont have any other server configured for prod.");
  }

  private DriverManagerDataSource getDriverManagerForGoogleCloudSql() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    driverManagerDataSource.setDriverClassName(env.getProperty("jdbc.cloud_sql.driverClassName"));
    driverManagerDataSource.setUrl(env.getProperty("jdbc.cloud_sql.url"));
    driverManagerDataSource.setUsername("root");
    driverManagerDataSource.setPassword("password");
    return driverManagerDataSource;
  }

}

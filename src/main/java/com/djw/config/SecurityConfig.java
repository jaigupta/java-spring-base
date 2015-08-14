package com.djw.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.djw.web.auth.LoginHandler;

@Configuration
@EnableWebSecurity
// If there is any configuration which can not be included in annotation based config,
// Move it to xml based config imported below. This xml based config is imported after
// AppConfig which provides the basic configurations and before SecurityConfig.
//@ImportResource({
//  "classpath:application-context.xml",
//  "classpath:spring-security.xml"
//  })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  Environment env;
  @Autowired
  DataSource dataSource;
  @Autowired
  LoginHandler loginHandler;

  private static final String[] PATH_PROTECTED_BY_USER_ROLE  = {
    "/user/**",
    "/post/new**",
    "/post/comment/new**",
    "/post/upvote/**",
    "/post/downvote/**",
    // TODO(jaigupta): change all services to put all auth pages under
    // /service_name/ap/** paths
  };

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth)
      throws Exception {

    String databaseName = env.getProperty("jdbc.databaseName");
    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(
            "select username,password,enabled from user where username=?")
        .authoritiesByUsernameQuery(
            "SELECT user.username, role.role FROM (" + databaseName
                + ".user_role as role JOIN " + databaseName
                + ".user as user ON"
                + " role.auth_id = user.auth_id) where user.username=?");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers(PATH_PROTECTED_BY_USER_ROLE).hasRole("USER").and()
        .formLogin().loginPage("/login").failureUrl("/login?error")
        .usernameParameter("username").passwordParameter("password")
        .successHandler(loginHandler).and().logout()
        .logoutUrl("/login/invalidate").logoutSuccessUrl("/login?logout").and()
        .exceptionHandling().accessDeniedPage("/err/403").and().csrf();
  }
}

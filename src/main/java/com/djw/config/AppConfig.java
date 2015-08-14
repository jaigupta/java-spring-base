package com.djw.config;

import java.util.Properties;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
/**
 * This is how the flow goes.
 * Where ever you want to load a config you have a 
 * @author jaigupta
 *
 */
@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = {
    "com.djw.auth.dao",
    "com.djw.comment.dao",
    "com.djw.post.dao",
    })
@EnableTransactionManagement
@ComponentScan({
  "com.djw.auth",
  "com.djw.comment",
  "com.djw.core",
  "com.djw.database",
  "com.djw.entity",
  "com.djw.indexing",
  "com.djw.post",
  "com.djw.settings",
  "com.djw.web"})
// Load properties file before any imports
// Otherwise profiles etc may not get loaded properly.in the imports
@PropertySources(@PropertySource({
    "classpath:properties/application.properties",
    "classpath:properties/hibernate.properties"}))
@Import({
  DevConfig.class,
  AppEngineConfig.class})
public class AppConfig {

  @Autowired
  private Environment env;

  @Bean
  public AnnotationSessionFactoryBean sessionFactory(
      DriverManagerDataSource dataSource) {
    AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setAnnotatedClasses(new Class[] {
        com.djw.auth.entity.User.class, com.djw.auth.entity.UserProfile.class,
        com.djw.auth.entity.UserRole.class,
        com.djw.comment.entity.BaseComment.class,
        com.djw.post.entity.PostComment.class, com.djw.post.entity.Post.class,
        com.djw.post.entity.UserPostLike.class,
        com.djw.membership.entity.Membership.class, });
    sessionFactory.setHibernateProperties(jpaProperties());
    return sessionFactory;
  }

  @Bean
  public JpaTransactionManager transactionManager(
      LocalContainerEntityManagerFactoryBean entityManagerFactoryBean)
      throws Exception {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactoryBean
        .getObject());
    return transactionManager;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      DriverManagerDataSource dataSource) throws ClassNotFoundException {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

    entityManagerFactoryBean.setDataSource(dataSource);
    // TODO(jaigupta): Remove comment and membership entity. we dont want them generic.
    entityManagerFactoryBean.setPackagesToScan(
        "com.djw.auth.entity",
        "com.djw.comment.entity",
        "com.djw.post.entity",
        "com.djw.membership.entity");
    entityManagerFactoryBean
        .setPersistenceProviderClass(HibernatePersistence.class);

    Properties jpaProterties = jpaProperties();
    entityManagerFactoryBean.setJpaProperties(jpaProterties);

    return entityManagerFactoryBean;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    messageSource.setBasename("i18n/messages");
    messageSource.setUseCodeAsDefaultMessage(true);

    return messageSource;
  }

  Properties jpaProperties() {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
    hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    hibernateProperties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
    // use create-drop / update
    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    hibernateProperties.setProperty("hibernate.user_sql_comments", env.getProperty("hibernate.user_sql_comments"));
    hibernateProperties.setProperty("hibernate.ejb.naming_strategy", env.getProperty("hibernate.ejb.naming_strategy"));

    return hibernateProperties;
  }

  @Bean
  public Boolean useSolrForIndexing() {
    return false;
  }
}

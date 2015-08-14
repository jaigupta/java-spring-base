package com.djw.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactoryBean;

import com.djw.config.Profiles.NoProfile;

@Configuration
@EnableSolrRepositories("com.djw.indexing.solr.dao")
@PropertySource("classpath:properties/dev/solr.properties")
// Disable this context. Use HttpSolrContext for all profiles as we are not using them.
@NoProfile
public class EmbeddedSolrContext {

  @Resource
  private Environment environment;

  @Bean
  public EmbeddedSolrServerFactoryBean embeddedSolrFactoy() throws Exception {
    EmbeddedSolrServerFactoryBean factory = new EmbeddedSolrServerFactoryBean();

    factory.setSolrHome(environment.getRequiredProperty("solr.solr.home"));
    factory.afterPropertiesSet();
    factory.getCores();

    return factory;
  }

  @Bean
  public SolrTemplate solrTemplate() throws Exception {
    return new SolrTemplate(embeddedSolrFactoy().getObject());
  }
}
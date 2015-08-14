package com.djw.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;

public class Profiles {

  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Profile("dev")
  public @interface DevProfile {
  }

  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Profile("prod")
  public @interface ProdProfile {
  }

  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Profile("appengine")
  public @interface AppEngineProfile {
    
  }

  // Use this annotation to make a configuration inert. This won't
  // get loaded in either prod or dev
  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Profile("none")
  public @interface NoProfile {
  }
}

package com.SEII.auth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{
  
  private static final String[] publicResources = new String[]{"/api/person/add",
      "/api/category/**", "/api/roles/**"};
  private static final String[] userResources = new String []{ 
      "/api/shopping-cart/**", "/api/post/**", "/api/all"};


  
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers(publicResources).permitAll()
        .antMatchers(userResources).hasAuthority("ROLE_USER").
        and().cors().disable();
  }

}

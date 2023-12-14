package com.gucardev.projectservice.config;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

  @Bean
  public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
      @Value("${spring.security.user.name}") String username,
      @Value("${spring.security.user.password}") String password) {
    return new BasicAuthRequestInterceptor(username, password);
  }

  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  public FeignClientInterceptor feignClientInterceptor() {
    return new FeignClientInterceptor();
  }
}

package com.gucardev.projectservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        .formLogin(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            x ->
                x.requestMatchers("/project/admin")
                    .hasAuthority("admin")
                    .requestMatchers("/project/mod")
                    .hasAuthority("mod")
                    .anyRequest()
                    .authenticated())
        .httpBasic(Customizer.withDefaults());
    httpSecurity.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);

    return httpSecurity.build();
  }
}

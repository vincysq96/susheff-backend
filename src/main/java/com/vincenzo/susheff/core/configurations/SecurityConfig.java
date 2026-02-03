package com.vincenzo.susheff.core.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableMethodSecurity()
@RequiredArgsConstructor
public class SecurityConfig {

  @Bean
  @Order(1)
  SecurityFilterChain authEndpoints(HttpSecurity http) throws Exception {
    http.securityMatcher(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/webjars/**",
            "/v3/api-docs/**",
            "/API/**"
        )
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        //.oauth2ResourceServer(AbstractHttpConfigurer::disable); // disabilita BearerTokenAuthenticationFilter
    return http.build();
  }

  @Bean
  @Order(2)
  SecurityFilterChain configure(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
    http
      .cors(cors -> cors.configurationSource(corsConfigurationSource))
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

    return http.build();
  }


}
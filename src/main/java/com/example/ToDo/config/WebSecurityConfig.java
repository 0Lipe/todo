package com.example.ToDo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(crsf -> crsf.disable())
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(HttpMethod.GET, "/todo/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/todo/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/todo/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/todo/**").permitAll()
                        .anyRequest()
                        .authenticated());


        return http.build();
    }}

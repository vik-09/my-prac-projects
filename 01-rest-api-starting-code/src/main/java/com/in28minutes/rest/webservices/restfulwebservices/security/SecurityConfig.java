package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filter(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors();
		httpSecurity.authorizeHttpRequests(
				auth -> auth.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated());
		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		httpSecurity.csrf().disable();
		return httpSecurity.build();
	}

}

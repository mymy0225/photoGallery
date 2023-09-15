package com.example.photoGallery.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
			.mvcMatchers("/users/login", "/users/sign_up").permitAll()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/users/login")
			.loginProcessingUrl("/login")
			.usernameParameter("email")
			.passwordParameter("password")
			.defaultSuccessUrl("/photos/", true)
			.failureUrl("/login?error")
			.permitAll()
		.and()
			.logout()
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/users/login");
		return http.build();
	}
}

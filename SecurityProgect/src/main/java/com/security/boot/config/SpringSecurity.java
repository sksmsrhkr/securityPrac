package com.security.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SpringSecurity {

	
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
        http
        .csrf(AbstractHttpConfigurer::disable)
        
        .authorizeHttpRequests((authorizeRequests) -> 
        	authorizeRequests
        		.requestMatchers("/**", "/user/**").permitAll()
				.anyRequest().authenticated()
        )
       
        .formLogin(formLogin -> formLogin
        		.loginPage("/user/login.html")
        		.loginProcessingUrl("/user/login.html")
        		.usernameParameter("userEmail")
	        .passwordParameter("userPw")
	        .failureUrl("/user/error.html")
	        .defaultSuccessUrl("/board/list", true));
       
        return http.build();
	}
	
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
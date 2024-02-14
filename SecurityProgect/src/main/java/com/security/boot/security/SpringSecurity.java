package com.security.boot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.security.boot.oauth2.PrincipalOauth2UserService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
//@AllArgsConstructor
@Configuration
@RequiredArgsConstructor
public class SpringSecurity {

	private final PrincipalOauth2UserService principalOauth2UserService;
	
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
        	.requestMatchers("/image/**", "/css/**").permitAll()
        	.requestMatchers("/user/**", "/", "/board/**", "/oauth2/kakao" , "/list").permitAll()
//        	.requestMatchers("/board/list").hasRole("USER")
			.anyRequest().authenticated()
        )

       
        .formLogin(formLogin -> formLogin
        		.loginPage("/user/login")
        		.permitAll()
        		.loginProcessingUrl("/user/login")
        		.usernameParameter("userEmail")
	        .passwordParameter("userPw")
	        .failureUrl("/user/error")
	        .defaultSuccessUrl("/", true))
        .logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true))
        
        .oauth2Login((oauth2) -> oauth2
        		.loginPage("/oauth2/authorization/**")
        		.defaultSuccessUrl("/", true)
        		.failureUrl("/oauth2/authorization/kakao")
                .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                .userService(principalOauth2UserService)))
        		;
       
        return http.build();
	}
	
//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    
}
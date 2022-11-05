package com.example.tj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.tj.service.JpaUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JpaUserDetailsService jpaUserDetailsService;
	
	public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
		super();
		this.jpaUserDetailsService = jpaUserDetailsService;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http
//				.authorizeRequests(auth -> auth.anyRequest().authenticated())
//				.build();
		
		return http
				.csrf().disable()
                .authorizeRequests(auth -> auth
                        .antMatchers("/h2-console/**").permitAll()
                        .antMatchers("/signup", "/styles/**", "/images/**", "/category/**").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(jpaUserDetailsService)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .formLogin()
                .loginPage("/login").permitAll() 
                .failureUrl("/login-error")
                .and()
                .logout(logout -> logout                                                
                        .logoutUrl("/logout")                                            
                        .logoutSuccessUrl("/")
                        .clearAuthentication(true)
                        .deleteCookies()
                        .invalidateHttpSession(true)                                                                         
                 )
                .build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

package com.sets.IncomeService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sets.IncomeService.security.JwtAuthFilter;

@Configuration
public class SecurityConfig {

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf(csrf-> csrf.disable())
//		    .authorizeHttpRequests(auth->auth.requestMatchers("/income").authenticated().anyRequest().permitAll())
//		    .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
//		    .formLogin(formLogin->formLogin.disable())
//		    .httpBasic(httpbasic->httpbasic.disable());
//	    http.addFilterBefore(new JwtValidationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//		return http.build();
	
	private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        	.formLogin(formlogin->formlogin.disable())
        	.httpBasic(httpbasic->httpbasic.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/income/**").authenticated() // protect these
                .anyRequest().permitAll()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
	
}


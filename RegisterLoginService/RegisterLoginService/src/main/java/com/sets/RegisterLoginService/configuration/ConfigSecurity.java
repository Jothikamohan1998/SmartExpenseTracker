package com.sets.RegisterLoginService.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sets.RegisterLoginService.security.AuthEntryPointJwt;
import com.sets.RegisterLoginService.security.JwtExtractFilter;
import com.sets.RegisterLoginService.service.CustomUserDetailsService;

@Configuration
public class ConfigSecurity {
	
	@Autowired
    CustomUserDetailsService userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Bean
    public JwtExtractFilter authenticationJwtTokenFilter() {
        return new JwtExtractFilter();
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{

		http.csrf(csrf -> csrf.disable()) // Disable CSRF
				.cors(cors -> cors.disable()) // Disable CORS (or configure if needed)
				.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedHandler))
				.sessionManagement(
						sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorizeRequests -> authorizeRequests
						.requestMatchers(HttpMethod.GET, "/login-register").permitAll()
						.requestMatchers(HttpMethod.GET, "/login-register/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/login-register/login").permitAll()
						.requestMatchers(HttpMethod.GET, "/login-register/register").permitAll()
						.requestMatchers(HttpMethod.POST, "/login-register/register").permitAll()
						.requestMatchers("/css/**", "/js/**").permitAll()
						.anyRequest().authenticated());
		// Add the JWT Token filter before the UsernamePasswordAuthenticationFilter
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}

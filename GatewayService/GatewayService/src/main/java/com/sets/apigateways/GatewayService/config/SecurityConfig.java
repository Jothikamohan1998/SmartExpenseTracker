package com.sets.apigateways.GatewayService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
	
//	@Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//		System.out.println("inside SecurityConfig");
//        return http
//            .csrf(ServerHttpSecurity.CsrfSpec::disable)
//            .authorizeExchange(exchange -> exchange
//                // Public endpoints
//                .pathMatchers(
//                		"/login-register/",
//                    "/login-register/login",
//                    "/login-register/register",
//                    "/css/**",
//                    "/js/**",
//                    "/favicon.ico"
//                ).permitAll()
//                // Everything else is secured
//                .anyExchange().authenticated()
//            )
//            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable) // 🚫 disable Basic Auth
//            .formLogin(ServerHttpSecurity.FormLoginSpec::disable) // 🚫 disable Form Login
//            .build();
//		}
	
	@Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        System.out.println("inside SecurityConfig");
        return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchange -> exchange
                .anyExchange().permitAll() // ✅ Let every request go through filters
            )
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable) // disable Basic Auth
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable) // disable Form Login
            .build();
    }
	
}

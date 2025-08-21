package com.sets.apigateways.GatewayService.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class JwtCookieToHeaderFilter implements GlobalFilter, Ordered {

    private final JwtService jwtService; // Your service to parse/validate tokens

    public JwtCookieToHeaderFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();

        // Allow public endpoints without JWT
        if (path.startsWith("/login-register/login") || 
            path.startsWith("/login-register/register") || 
            path.startsWith("/login-register/")) {
            return chain.filter(exchange);
        }

        // Read JWT from cookie
        HttpCookie jwtCookie = exchange.getRequest().getCookies().getFirst("jwt");
        if (jwtCookie == null) {
            System.out.println("No JWT cookie found");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = jwtCookie.getValue();
        System.out.println("JWT from cookie: " + token);

        // Validate the JWT token
        if (!jwtService.validateJwtToken(token)) {
            System.out.println("Invalid or expired JWT");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Optional: Extract username for logging
        String username = jwtService.getUsername(token);
        System.out.println("Authenticated user: " + username);
        
     // Forward token as Authorization header
        ServerHttpRequest mutatedRequest = exchange.getRequest()
                .mutate()
                .header("Authorization", "Bearer " + token)
                .build();

        return chain.filter(exchange.mutate().request(mutatedRequest).build());


        // Proceed without modifying headers — purely cookie based
//        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -2; // Run early
    }
}




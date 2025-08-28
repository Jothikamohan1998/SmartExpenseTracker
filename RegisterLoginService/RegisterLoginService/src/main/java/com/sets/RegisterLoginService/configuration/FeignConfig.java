package com.sets.RegisterLoginService.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sets.RegisterLoginService.security.JwtTokenHolder;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;


@Configuration
public class FeignConfig {
	  @Bean
	    public RequestInterceptor feignAuthInterceptor() {
	        return template -> {
	        	String token = JwtTokenHolder.getToken();
	        	System.out.println("token from jwt token holder :  "+token);
	            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	            if (attrs != null) {
	                HttpServletRequest request = attrs.getRequest();

	                // Read JWT from cookie
	                if (request.getCookies() != null) {
	                    for (Cookie cookie : request.getCookies()) {
	                        if ("jwt".equals(cookie.getName())) {
	                            token = cookie.getValue();
	                            break;
	                        }
	                    }
	                }

	                if (token != null) {
	                    template.header("Authorization", "Bearer " + token);
	                    System.out.println("Forwarding JWT via Feign: " + token);
	                } else {
	                    System.out.println("No JWT found for Feign request");
	                }
	            } else {
	                System.out.println("Request attributes null");
	            }
	        };
	    }

	}
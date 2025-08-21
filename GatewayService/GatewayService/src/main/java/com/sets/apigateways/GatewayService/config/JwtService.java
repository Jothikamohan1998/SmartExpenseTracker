package com.sets.apigateways.GatewayService.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
	public class JwtService {

	 @Value("${jwt.secret}")
	    private String jwtSecret;

	    private SecretKey secretKey;

//	    @PostConstruct
//	    public void init() {
//	        secretKey = Keys.hmacShaKeyFor(io.jsonwebtoken.io.Decoders.BASE64.decode(jwtSecret));
//	    }
	    
	    @PostConstruct
	    public void init() {
	    	secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	    }
	    
	    public boolean validateJwtToken(String token) {
	        try {
	        	
	        	System.out.println("inside validate token");
	            Jwts.parserBuilder()
	                .setSigningKey(secretKey) // no deprecated methods
	                .build()
	                .parseClaimsJws(token);
	            return true;
	        } catch (JwtException e) {
	            System.out.println("Invalid JWT: " + e.getMessage());
	        }
	        return false;
	    }

	    public String getUsername(String token) {
	        Claims claims = Jwts.parserBuilder()
	            .setSigningKey(secretKey)
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	        return claims.getSubject();
	    }
}

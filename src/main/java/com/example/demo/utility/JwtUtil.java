package com.example.demo.utility;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtUtil {
	
	 public String extractId(String token) {
	        String[] auth = token.split(" ");
	        extractAllClaims(auth[1]);
	        return (String) extractClaim(auth[1], "sub");
	    }

	    public Object extractClaim(String token, String key) {
	        final Map<String, Claim> claims = extractAllClaims(token);
	        return claims.get(key).asString();
	    }

	    private final  Map<String, Claim> extractAllClaims(String token) {
	        DecodedJWT jwtoriginal = JWT.decode(token);
	        Map<String, Claim> claims = jwtoriginal.getClaims();
//	        claims.forEach((k,v) -> System.out.println("KEY = "+k + ", VALUE ="+v));
	        return claims;
	    }
	
	
	
	
	

}

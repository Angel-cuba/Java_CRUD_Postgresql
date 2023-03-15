package com.crud.crudpostgres.config;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtService {

  private static final String SECRET_KEY = "442A472D4B6150645367566B597033733676397924423F4528482B4D62516554";

  public String extractUserName(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(
    Map<String, Object> extraClaims,
    UserDetails userDetails
  ){
    return Jwts
    .builder()
    .setClaims(extraClaims)
    .setSubject(userDetails.getUsername())
    .setIssuedAt(new Date(System.currentTimeMillis()))
    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
    .compact();
  }

  private Claims extractAllClaims(String jwt) {
    return Jwts
    .parserBuilder()
    .setSigningKey(getSignInKey())
    .build()
    .parseClaimsJws(jwt)
    .getBody();
  }

	private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
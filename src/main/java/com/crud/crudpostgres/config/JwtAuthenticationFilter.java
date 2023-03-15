package com.crud.crudpostgres.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain
    )  throws ServletException, IOException {
      final String authorizationHeader = request.getHeader("Authorization");
      final String jwt;
      if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
        filterChain.doFilter(request, response);
        return;
      }
      jwt = authorizationHeader.substring(7);
  }
  
}
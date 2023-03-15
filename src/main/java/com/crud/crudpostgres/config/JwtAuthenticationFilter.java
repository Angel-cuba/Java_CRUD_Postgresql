package com.crud.crudpostgres.config;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain
    )  throws ServletException, IOException {
      final String authorizationHeader = request.getHeader("Authorization");
      final String jwt;
      final String userEmail;
      if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
        filterChain.doFilter(request, response);
        return;
      }
      jwt = authorizationHeader.substring(7);
      userEmail = jwtService.extractUserName(jwt);
      if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
       UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
      }
  }
  
}

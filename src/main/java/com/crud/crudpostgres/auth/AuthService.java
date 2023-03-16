package com.crud.crudpostgres.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.crudpostgres.config.JwtService;
import com.crud.crudpostgres.interfaces.UserRepository;
import com.crud.crudpostgres.model.Role;
import com.crud.crudpostgres.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;
  
  private JwtService jwtService;

  private final AuthenticationManager authenticationManager;
  
  public AuthenticationResponse register(RegisterRequest registerRequest) {
    var user = User.builder()
      .name(registerRequest.getFistname())
      .lastName(registerRequest.getLastname())
      .email(registerRequest.getEmail())
      .password(passwordEncoder.encode(registerRequest.getPassword()))
      .role(Role.USER)
      .build();

    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return  AuthenticationResponse.builder()
              .token(jwtToken)
              .build();
  }

public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
  authenticationManager.authenticate(
    new UsernamePasswordAuthenticationToken(
      authenticationRequest.getEmail(),
      authenticationRequest.getPassword()
    )
  );
  var user = userRepository.findByEmail(authenticationRequest.getEmail())
    .orElseThrow(() -> new RuntimeException("User not found"));
  
  var jwtToken = jwtService.generateToken(user);
  return AuthenticationResponse.builder()
    .token(jwtToken)
    .build();
}
  
}

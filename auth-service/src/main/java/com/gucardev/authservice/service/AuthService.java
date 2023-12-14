package com.gucardev.authservice.service;

import com.gucardev.authservice.dto.AuthResponse;
import com.gucardev.authservice.dto.LoginRequest;
import com.gucardev.authservice.dto.TokenDto;
import com.gucardev.authservice.model.Role;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;
  private final UserService userService;

  public TokenDto login(LoginRequest loginRequest) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequest.getUsername(), loginRequest.getPassword()));
      return tokenService.generateTokenPairs(loginRequest.getUsername());
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

  public AuthResponse verifyToken(String token) {
    var decodedJWT = tokenService.verifyJWT(token);
    var user = userService.getByUsername(decodedJWT.getSubject());
    return new AuthResponse(
        user.getUsername(),
        user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
  }
}

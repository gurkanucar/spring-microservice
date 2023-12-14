package com.gucardev.authservice.controller;

import com.gucardev.authservice.dto.AuthResponse;
import com.gucardev.authservice.dto.LoginRequest;
import com.gucardev.authservice.dto.TokenDto;
import com.gucardev.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok().body(authService.login(loginRequest));
  }

  @GetMapping("/validate")
  public ResponseEntity<AuthResponse> login(@RequestParam(name = "token") String token) {
    return ResponseEntity.ok().body(authService.verifyToken(token));
  }
}

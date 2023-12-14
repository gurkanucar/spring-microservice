package com.gucardev.projectservice.service;

import com.gucardev.projectservice.client.AuthClient;
import com.gucardev.projectservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final AuthClient authClient;

  public UserDto getAuthenticatedUser() {
    return authClient
        .getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
        .getBody();
  }
}

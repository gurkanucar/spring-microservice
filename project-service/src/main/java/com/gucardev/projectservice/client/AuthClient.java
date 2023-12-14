package com.gucardev.projectservice.client;

import com.gucardev.projectservice.config.FeignClientConfig;
import com.gucardev.projectservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", path = "/auth-service", configuration = FeignClientConfig.class)
public interface AuthClient {

  @GetMapping("/users/user/{username}")
  ResponseEntity<UserDto> getUserByUsername(@PathVariable String username);
}

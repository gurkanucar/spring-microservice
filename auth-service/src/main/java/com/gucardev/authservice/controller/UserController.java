package com.gucardev.authservice.controller;

import com.gucardev.authservice.dto.UserDto;
import com.gucardev.authservice.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user/{username}")
  public UserDto getUsers(@PathVariable String username) {
    return userService.getDtoByUsername(username);
  }

  @GetMapping
  public List<UserDto> getUsers() {
    return userService.getUsers();
  }

  @PostMapping
  public UserDto createUser(@RequestBody UserDto userDto) {
    return userService.createUser(userDto);
  }
}

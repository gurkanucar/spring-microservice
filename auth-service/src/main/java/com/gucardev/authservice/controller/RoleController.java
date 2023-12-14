package com.gucardev.authservice.controller;

import com.gucardev.authservice.dto.RoleDto;
import com.gucardev.authservice.service.RoleService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

  private final RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  public List<RoleDto> getRoles() {
    return roleService.getRoles();
  }

  @PostMapping
  public RoleDto createRole(@RequestBody RoleDto userDto) {
    return roleService.createRole(userDto);
  }
}

package com.gucardev.projectservice.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends BaseDto {

  private Long id;

  private String name;

  private String username;

  private String password;

  private boolean isEnabled;

  private Set<RoleDto> roles;
}

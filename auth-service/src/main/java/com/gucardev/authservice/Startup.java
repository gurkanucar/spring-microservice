package com.gucardev.authservice;

import com.gucardev.authservice.dto.RoleDto;
import com.gucardev.authservice.dto.UserDto;
import com.gucardev.authservice.service.RoleService;
import com.gucardev.authservice.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Startup implements CommandLineRunner {

  private final RoleService roleService;
  private final UserService userService;

  @Value("${spring.security.user.name}")
  private String username;

  @Value("${spring.security.user.password}")
  private String password;

  @Override
  public void run(String... args) {
    createDummyData();
  }

  private void createDummyData() {
    RoleDto serviceRol = new RoleDto();
    serviceRol.setName("SERVICE");
    serviceRol = roleService.createRole(serviceRol);

    // created for microservice authentication while requesting to auth-service
    UserDto service = new UserDto();
    service.setName("SERVICE");
    service.setUsername(username);
    service.setPassword(password);
    service.setEnabled(true);
    service.setRoles(Set.of(serviceRol));
    userService.createUser(service);

    // create initial users and roles

    RoleDto adminRole = new RoleDto();
    adminRole.setName("ADMIN");
    adminRole = roleService.createRole(adminRole);

    RoleDto userRole = new RoleDto();
    userRole.setName("USER");
    userRole = roleService.createRole(userRole);

    RoleDto modRole = new RoleDto();
    modRole.setName("MODERATOR");
    modRole = roleService.createRole(modRole);

    UserDto admin = new UserDto();
    admin.setName("admin");
    admin.setUsername("grkn");
    admin.setPassword("pass");
    admin.setEnabled(true);
    admin.setRoles(Set.of(adminRole, modRole));
    userService.createUser(admin);

    UserDto user = new UserDto();
    user.setName("user");
    user.setUsername("user");
    user.setPassword("pass");
    user.setEnabled(true);
    user.setRoles(Set.of(userRole));
    userService.createUser(user);

    UserDto user2 = new UserDto();
    user2.setName("user2");
    user2.setUsername("user2");
    user2.setPassword("pass");
    user2.setRoles(Set.of(userRole));
    userService.createUser(user2);

    UserDto mod = new UserDto();
    mod.setName("mod");
    mod.setUsername("mod");
    mod.setPassword("pass");
    mod.setEnabled(true);
    mod.setRoles(Set.of(modRole));
    userService.createUser(mod);
  }
}

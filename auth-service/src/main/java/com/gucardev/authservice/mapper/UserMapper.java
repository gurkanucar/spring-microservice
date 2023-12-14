package com.gucardev.authservice.mapper;

import com.gucardev.authservice.dto.UserDto;
import com.gucardev.authservice.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto toDto(User entity);

  User toEntity(UserDto entity);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updatePartial(@MappingTarget User entity, UserDto dto);
}

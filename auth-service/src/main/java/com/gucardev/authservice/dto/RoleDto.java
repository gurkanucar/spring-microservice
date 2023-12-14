package com.gucardev.authservice.dto;

import com.gucardev.authservice.validation.CreateValidationGroup;
import com.gucardev.authservice.validation.UpdateValidationGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto extends BaseDto {

  @Null(groups = {CreateValidationGroup.class})
  @NotNull(groups = {UpdateValidationGroup.class})
  private Long id;

  private String name;
}

package com.gucardev.projectservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto extends BaseDto {

  @NotNull private Long id;
  @NotNull private Long userId;
  @NotBlank private String projectName;
  @NotBlank private String projectDetails;
}

package com.gucardev.projectservice.dto;

import com.gucardev.projectservice.validations.CreateValidationGroup;
import com.gucardev.projectservice.validations.UpdateValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest extends BaseDto {

  @NotNull(groups = UpdateValidationGroup.class)
  @Null(groups = CreateValidationGroup.class)
  private Long id;

  @NotNull private Long userId;
  @NotBlank private String projectName;
  @NotBlank private String projectDetails;
}

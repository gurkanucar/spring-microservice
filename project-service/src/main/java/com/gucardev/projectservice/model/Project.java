package com.gucardev.projectservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Project extends BaseEntity {

  private Long userId;
  private String projectName;
  private String projectDetails;
}

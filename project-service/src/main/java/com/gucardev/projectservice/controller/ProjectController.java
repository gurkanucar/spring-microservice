package com.gucardev.projectservice.controller;

import com.gucardev.projectservice.dto.ProjectDto;
import com.gucardev.projectservice.dto.ProjectRequest;
import com.gucardev.projectservice.service.ProjectService;
import com.gucardev.projectservice.validations.CreateValidationGroup;
import com.gucardev.projectservice.validations.UpdateValidationGroup;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

  private final ProjectService projectService;

  @PostMapping
  public ResponseEntity<ProjectDto> createProject(
      @Validated(CreateValidationGroup.class) @RequestBody ProjectRequest request) {
    ProjectDto createdProject = projectService.create(request);
    return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
    ProjectDto project = projectService.getById(id);
    return ResponseEntity.ok(project);
  }

  @GetMapping
  public ResponseEntity<List<ProjectDto>> getAllProjects() {
    List<ProjectDto> projects = projectService.getAll();
    return ResponseEntity.ok(projects);
  }

  @PutMapping
  public ResponseEntity<ProjectDto> updateProject(
      @Validated(UpdateValidationGroup.class) @RequestBody ProjectRequest request) {
    ProjectDto updatedProject = projectService.update(request);
    return ResponseEntity.ok(updatedProject);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
    projectService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

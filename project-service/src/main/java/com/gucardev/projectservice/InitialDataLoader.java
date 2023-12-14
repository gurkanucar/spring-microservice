package com.gucardev.projectservice;

import com.gucardev.projectservice.dto.ProjectRequest;
import com.gucardev.projectservice.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialDataLoader implements CommandLineRunner {

  private final ProjectService projectService;

  @Override
  public void run(String... args) {
    loadInitialProjects();
  }

  private void loadInitialProjects() {
    List<ProjectRequest> initialProjects = createSampleProjects();

    for (ProjectRequest project : initialProjects) {
      projectService.create(project);
    }
  }

  private List<ProjectRequest> createSampleProjects() {
    return List.of(
        new ProjectRequest(null, 2L, "Project 1", "Details for Project 1"),
        new ProjectRequest(null, 2L, "Project 2", "Details for Project 2"),
        new ProjectRequest(null, 5L, "Project 3", "Details for Project 3"),
        new ProjectRequest(null, 2L, "Project 4", "Details for Project 4"),
        new ProjectRequest(null, 3L, "Project 5", "Details for Project 5"));
  }
}

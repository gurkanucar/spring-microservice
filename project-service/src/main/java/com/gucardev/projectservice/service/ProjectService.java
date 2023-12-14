package com.gucardev.projectservice.service;

import com.gucardev.projectservice.dto.ProjectDto;
import com.gucardev.projectservice.dto.ProjectRequest;
import com.gucardev.projectservice.dto.RoleDto;
import com.gucardev.projectservice.dto.UserDto;
import com.gucardev.projectservice.mapper.ProjectMapper;
import com.gucardev.projectservice.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

  private final ProjectMapper mapper;
  private final ProjectRepository repository;
  private final UserService userService;

  public ProjectDto create(ProjectRequest request) {
    return mapper.toDto(repository.save(mapper.toEntity(request)));
  }

  public ProjectDto getById(Long id) {
    return repository
        .findById(id)
        .map(mapper::toDto)
        .orElseThrow(() -> new EntityNotFoundException("not found!"));
  }

  public List<ProjectDto> getAllAdmin() {
    return repository.findAll().stream().map(mapper::toDto).toList();
  }

  public List<ProjectDto> getAll() {
    UserDto user = userService.getAuthenticatedUser();
    if (user.getRoles().stream().map(RoleDto::getName).anyMatch(x -> x.equals("ADMIN"))) {
      return getAllAdmin();
    }

    return repository.findAllByUserId(Objects.requireNonNull(user).getId()).stream()
        .map(mapper::toDto)
        .toList();
  }

  public ProjectDto update(ProjectRequest request) {
    var existing =
        repository
            .findById(request.getId())
            .orElseThrow(() -> new EntityNotFoundException("not found!"));

    // get authenticated user and then check ids
    UserDto user = userService.getAuthenticatedUser();
    if (!user.getId().equals(existing.getUserId())) {
      throw new RuntimeException("this user is not owner of project!");
    }

    mapper.update(request, existing);
    return mapper.toDto(repository.save(existing));
  }

  public void delete(Long id) {
    // get authenticated user and then check ids and if role admin delete
    var existing =
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found!"));
    repository.delete(existing);
  }
}

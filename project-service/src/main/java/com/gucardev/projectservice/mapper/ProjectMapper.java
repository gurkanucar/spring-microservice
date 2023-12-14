package com.gucardev.projectservice.mapper;

import com.gucardev.projectservice.dto.ProjectDto;
import com.gucardev.projectservice.dto.ProjectRequest;
import com.gucardev.projectservice.model.Project;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
  ProjectDto toDto(Project entity);

  Project toEntity(ProjectRequest dto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "id", ignore = true)
  void update(ProjectRequest dto, @MappingTarget Project entity);
}

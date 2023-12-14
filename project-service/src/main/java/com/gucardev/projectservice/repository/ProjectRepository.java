package com.gucardev.projectservice.repository;

import com.gucardev.projectservice.model.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

  List<Project> findAllByUserId(Long userId);

  @Query("SELECT COUNT(p), p.userId FROM Project p GROUP BY p.userId ORDER BY COUNT(p) DESC")
  List<Object[]> findMaxProjectCountForOnePerson();
}

package com.ajaysw.ProjectManagementSystem.respository;

import com.ajaysw.ProjectManagementSystem.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Ajay Wankhade
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {}


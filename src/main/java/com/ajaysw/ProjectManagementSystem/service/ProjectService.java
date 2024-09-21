package com.ajaysw.ProjectManagementSystem.service;

import com.ajaysw.ProjectManagementSystem.entity.ProjectDTO;
import com.ajaysw.ProjectManagementSystem.entity.TaskDTO;

import java.util.List;
/**
 * @author Ajay Wankhade
 */
public interface ProjectService {


    ProjectDTO createProject(ProjectDTO projectDTO);

    ProjectDTO updateProject(Long projectId, ProjectDTO projectDTO);

    void deleteProject(Long projectId);

    List<ProjectDTO> getAllProjects();

    ProjectDTO getProjectById(Long projectId);

    List<TaskDTO> getTasksByProject(Long projectId);
}

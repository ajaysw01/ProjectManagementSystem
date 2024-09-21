package com.ajaysw.ProjectManagementSystem.service;

import com.ajaysw.ProjectManagementSystem.entity.Project;
import com.ajaysw.ProjectManagementSystem.entity.ProjectDTO;
import com.ajaysw.ProjectManagementSystem.entity.Task;
import com.ajaysw.ProjectManagementSystem.entity.TaskDTO;
import com.ajaysw.ProjectManagementSystem.exception.ResourceNotFoundException;
import com.ajaysw.ProjectManagementSystem.respository.ProjectRepository;
import com.ajaysw.ProjectManagementSystem.respository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ajay Wankhade
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        Project savedProject = projectRepository.save(project);

        return convertToDTO(savedProject);
    }


    @Override
    public ProjectDTO updateProject(Long projectId, ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + projectId));

        existingProject.setName(projectDTO.getName());
        existingProject.setDescription(projectDTO.getDescription());
        return convertToDTO(projectRepository.save(existingProject));
    }


    @Override
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectById(Long projectId) {
        return convertToDTO(projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + projectId)));
    }

    @Override
    public List<TaskDTO> getTasksByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId).stream()
                .map(this::convertToTaskDTO)
                .collect(Collectors.toList());
    }

    private ProjectDTO convertToDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setTasks(project.getTasks().stream().map(this::convertToTaskDTO).collect(Collectors.toList()));
        return dto;
    }

    private TaskDTO convertToTaskDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setStatus(task.getStatus());
        return dto;
    }
}

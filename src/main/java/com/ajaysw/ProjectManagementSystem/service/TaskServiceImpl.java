package com.ajaysw.ProjectManagementSystem.service;

import com.ajaysw.ProjectManagementSystem.entity.*;
import com.ajaysw.ProjectManagementSystem.exception.ResourceNotFoundException;
import com.ajaysw.ProjectManagementSystem.exception.ResourceOverAllocationException;
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
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public TaskDTO createTask(Long projectId, TaskDTO taskDTO) { // Change parameter type
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + projectId));

        Task task = new Task(); // Create new Task entity
        task.setName(taskDTO.getName());
        task.setStatus(taskDTO.getStatus());
        task.setProject(project);

        return convertToDTO(taskRepository.save(task)); // Convert to DTO before returning
    }

    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO taskDTO) { // Change parameter type
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));

        existingTask.setName(taskDTO.getName());
        existingTask.setStatus(taskDTO.getStatus());

        return convertToDTO(taskRepository.save(existingTask)); // Convert to DTO before returning
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskDTO> getTasksByProject(Long projectId) { // Change return type
        return taskRepository.findByProjectId(projectId).stream()
                .map(this::convertToDTO) // Convert to DTO
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long taskId) { // Change return type
        return convertToDTO(taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId))); // Convert to DTO
    }

//    @Override
//    public TaskDTO allocateResourceToTask(Long taskId, Long resourceId) {
//        Task task = taskRepository.findById(taskId)
//                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
//
//        if (!resourceService.isResourceAvailable(resourceId)) {
//            throw new ResourceOverAllocationException("Resource is not available for allocation.");
//        }
//
//        long allocatedTaskCount = taskRepository.countByResourceIdAndProjectId(resourceId, task.getProject().getId());
//
//        if (allocatedTaskCount >= 2) {
//            throw new ResourceOverAllocationException("Resource is already allocated to 2 tasks in this project.");
//        }
//
//        ResourceDTO resource = resourceService.getResourceById(resourceId);
//        task.setResource(resource);
//        return taskRepository.save(task);
//    }

    @Override
    public TaskDTO allocateResourceToTask(Long taskId, Long resourceId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));

        if (!resourceService.isResourceAvailable(resourceId)) {
            throw new ResourceOverAllocationException("Resource is not available for allocation.");
        }

        long allocatedTaskCount = taskRepository.countByResourceIdAndProjectId(resourceId, task.getProject().getId());

        if (allocatedTaskCount >= 2) {
            throw new ResourceOverAllocationException("Resource is already allocated to 2 tasks in this project.");
        }

        // Retrieve the Resource entity, not DTO
        Resource resource = resourceService.getResourceEntityById(resourceId); // Create this method to return Resource

        task.setResource(resource); // Set the Resource entity on the Task

        // Save the task and convert to TaskDTO before returning
        Task savedTask = taskRepository.save(task);
        return convertToDTO(savedTask); // Convert saved Task to TaskDTO
    }



    // New method to convert Task to TaskDTO
    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setStatus(task.getStatus());
        return dto;
    }
}

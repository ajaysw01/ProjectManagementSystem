package com.ajaysw.ProjectManagementSystem.service;

import com.ajaysw.ProjectManagementSystem.entity.TaskDTO;

import java.util.List;

/**
 * @author Ajay Wankhade
 */
public interface TaskService {


    TaskDTO createTask(Long projectId, TaskDTO taskDTO);

    TaskDTO updateTask(Long taskId, TaskDTO taskDTO);

    void deleteTask(Long taskId);

    List<TaskDTO> getTasksByProject(Long projectId);

    TaskDTO getTaskById(Long taskId);

    TaskDTO allocateResourceToTask(Long taskId, Long resourceId);
}

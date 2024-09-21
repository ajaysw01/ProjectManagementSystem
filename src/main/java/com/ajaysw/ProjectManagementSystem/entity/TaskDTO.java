package com.ajaysw.ProjectManagementSystem.entity;

import com.ajaysw.ProjectManagementSystem.enums.TaskStatus;
import lombok.Data;

/**
 * @author Ajay Wankhade
 */
@Data
public class TaskDTO {
    private Long id;
    private String name;
    private TaskStatus status;
}

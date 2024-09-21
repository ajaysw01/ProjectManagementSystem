package com.ajaysw.ProjectManagementSystem.enums;

import com.ajaysw.ProjectManagementSystem.utils.TaskStatusDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Ajay Wankhade
 */
@JsonDeserialize(using = TaskStatusDeserializer.class)
public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED
}

package com.ajaysw.ProjectManagementSystem.entity;

import lombok.Data;

import java.util.List;

/**
 * @author Ajay Wankhade
 */
@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private List<TaskDTO> tasks;
}

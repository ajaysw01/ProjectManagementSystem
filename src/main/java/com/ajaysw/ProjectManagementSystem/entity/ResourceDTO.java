package com.ajaysw.ProjectManagementSystem.entity;

import com.ajaysw.ProjectManagementSystem.enums.ResourceStatus;
import lombok.Data;
/**
 * @author Ajay Wankhade
 */
@Data
public class ResourceDTO {
    private Long id;
    private String name;
    private ResourceStatus status; // You can use the enum directly
}

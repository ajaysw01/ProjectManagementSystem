package com.ajaysw.ProjectManagementSystem.enums;

import com.ajaysw.ProjectManagementSystem.utils.ResourceStatusDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Ajay Wankhade
 */
@JsonDeserialize(using = ResourceStatusDeserializer.class)
public enum ResourceStatus {
    AVAILABLE,
    UNAVAILABLE
}

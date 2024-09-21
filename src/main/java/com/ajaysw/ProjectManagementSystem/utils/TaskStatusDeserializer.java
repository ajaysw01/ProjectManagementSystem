package com.ajaysw.ProjectManagementSystem.utils;

import com.ajaysw.ProjectManagementSystem.enums.TaskStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @author Ajay Wankhade
 */
public class TaskStatusDeserializer extends JsonDeserializer<TaskStatus> {
    @Override
    public TaskStatus deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String value = jp.getText().toUpperCase(); // Convert to uppercase
        return TaskStatus.valueOf(value);
    }
}

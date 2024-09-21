package com.ajaysw.ProjectManagementSystem.controller;

import com.ajaysw.ProjectManagementSystem.entity.TaskDTO; // Import TaskDTO
import com.ajaysw.ProjectManagementSystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Ajay Wankhade
 */
@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task", description = "Task management API")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/{projectId}")
    @Operation(summary = "Create a new task", description = "Creates a new task for a specific project and returns the created task details")
    @ApiResponse(responseCode = "201", description = "Task created successfully", content = @Content(schema = @Schema(implementation = TaskDTO.class)))

    public ResponseEntity<TaskDTO> createTask(
            @Parameter(description = "ID of the project to create the task for") @PathVariable Long projectId,
            @RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.createTask(projectId, taskDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    @Operation(summary = "Update an existing task", description = "Updates an existing task and returns the updated task details")
    @ApiResponse(responseCode = "200", description = "Task updated successfully", content = @Content(schema = @Schema(implementation = TaskDTO.class)))

    public ResponseEntity<TaskDTO> updateTask(
            @Parameter(description = "ID of the task to update") @PathVariable Long taskId,
            @RequestBody TaskDTO taskDTO) { // Change to TaskDTO
        TaskDTO updatedTask = taskService.updateTask(taskId, taskDTO);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    @Operation(summary = "Delete a task", description = "Deletes a task by its ID")
    @ApiResponse(responseCode = "204", description = "Task deleted successfully")

    public ResponseEntity<Void> deleteTask(
            @Parameter(description = "ID of the task to delete") @PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{taskId}")
    @Operation(summary = "Get a task by ID", description = "Retrieves a task by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the task", content = @Content(schema = @Schema(implementation = TaskDTO.class)))

    public ResponseEntity<TaskDTO> getTaskById(
            @Parameter(description = "ID of the task to retrieve") @PathVariable Long taskId) { // Change return type to TaskDTO
        TaskDTO task = taskService.getTaskById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/{taskId}/allocate-resource/{resourceId}")
    @Operation(summary = "Allocate a resource to a task", description = "Allocates a specific resource to a task and returns the updated task details")
    @ApiResponse(responseCode = "200", description = "Resource allocated successfully", content = @Content(schema = @Schema(implementation = TaskDTO.class)))
    public ResponseEntity<?> allocateResourceToTask(
            @Parameter(description = "ID of the task to allocate the resource to") @PathVariable Long taskId,
            @Parameter(description = "ID of the resource to allocate") @PathVariable Long resourceId) {
        TaskDTO updatedTask = taskService.allocateResourceToTask(taskId, resourceId);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
}

package com.ajaysw.ProjectManagementSystem.controller;

import com.ajaysw.ProjectManagementSystem.entity.ProjectDTO;
import com.ajaysw.ProjectManagementSystem.entity.TaskDTO;
import com.ajaysw.ProjectManagementSystem.service.ProjectService;
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

import java.util.List;

/**
 * @author Ajay Wankhade
 */
@RestController
@RequestMapping("/api/projects")
@Tag(name = "Project", description = "Project management API")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    @Operation(summary = "Create a new project", description = "Creates a new project and returns the created project details")
    @ApiResponse(responseCode = "201", description = "Project created successfully", content = @Content(schema = @Schema(implementation = ProjectDTO.class)))
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PutMapping("/{projectId}")
    @Operation(summary = "Update an existing project", description = "Updates an existing project and returns the updated project details")
    @ApiResponse(responseCode = "200", description = "Project updated successfully", content = @Content(schema = @Schema(implementation = ProjectDTO.class)))
    public ResponseEntity<ProjectDTO> updateProject(
            @Parameter(description = "ID of the project to update") @PathVariable Long projectId,
            @RequestBody ProjectDTO projectDTO) {
        ProjectDTO updatedProject = projectService.updateProject(projectId, projectDTO);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    @Operation(summary = "Delete a project", description = "Deletes a project by its ID")
    @ApiResponse(responseCode = "204", description = "Project deleted successfully")
    public ResponseEntity<Void> deleteProject(
            @Parameter(description = "ID of the project to delete") @PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @Operation(summary = "Get all projects", description = "Retrieves a list of all projects")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of projects", content = @Content(schema = @Schema(implementation = ProjectDTO.class)))

    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    @Operation(summary = "Get a project by ID", description = "Retrieves a project by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the project", content = @Content(schema = @Schema(implementation = ProjectDTO.class)))

    public ResponseEntity<ProjectDTO> getProjectById(
            @Parameter(description = "ID of the project to retrieve") @PathVariable Long projectId) {
        ProjectDTO project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/tasks")
    @Operation(summary = "Get tasks for a project", description = "Retrieves a list of tasks associated with a specific project")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tasks", content = @Content(schema = @Schema(implementation = TaskDTO.class)))
    public ResponseEntity<List<TaskDTO>> getTasksByProject(
            @Parameter(description = "ID of the project to retrieve tasks for") @PathVariable Long projectId) {
        List<TaskDTO> tasks = projectService.getTasksByProject(projectId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}

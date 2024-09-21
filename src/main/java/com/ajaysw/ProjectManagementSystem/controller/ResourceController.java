package com.ajaysw.ProjectManagementSystem.controller;

import com.ajaysw.ProjectManagementSystem.entity.ResourceDTO;
import com.ajaysw.ProjectManagementSystem.service.ResourceService;
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
@RequestMapping("/api/resources")
@Tag(name = "Resource", description = "Resource management API")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping
    @Operation(summary = "Create a new resource", description = "Creates a new resource and returns the created resource details")
    @ApiResponse(responseCode = "201", description = "Resource created successfully", content = @Content(schema = @Schema(implementation = ResourceDTO.class)))
    public ResponseEntity<ResourceDTO> createResource(@RequestBody ResourceDTO resourceDTO) {
        ResourceDTO createdResource = resourceService.createResource(resourceDTO);
        return new ResponseEntity<>(createdResource, HttpStatus.CREATED);
    }

    @PutMapping("/{resourceId}")
    @Operation(summary = "Update an existing resource", description = "Updates an existing resource and returns the updated resource details")
    @ApiResponse(responseCode = "200", description = "Resource updated successfully", content = @Content(schema = @Schema(implementation = ResourceDTO.class)))

    public ResponseEntity<ResourceDTO> updateResource(@PathVariable Long resourceId, @RequestBody ResourceDTO resourceDTO) { // Change to ResourceDTO
        ResourceDTO updatedResource = resourceService.updateResource(resourceId, resourceDTO);
        return new ResponseEntity<>(updatedResource, HttpStatus.OK);
    }

    @DeleteMapping("/{resourceId}")
    @Operation(summary = "Delete a resource", description = "Deletes a resource by its ID")
    @ApiResponse(responseCode = "204", description = "Resource deleted successfully")
    public ResponseEntity<Void> deleteResource(
            @Parameter(description = "ID of the resource to delete") @PathVariable Long resourceId) {
        resourceService.deleteResource(resourceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @Operation(summary = "Get all resources", description = "Retrieves a list of all resources")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of resources", content = @Content(schema = @Schema(implementation = ResourceDTO.class)))

    public ResponseEntity<List<ResourceDTO>> getAllResources() {
        List<ResourceDTO> resources = resourceService.getAllResources();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{resourceId}")
    @Operation(summary = "Get a resource by ID", description = "Retrieves a resource by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the resource", content = @Content(schema = @Schema(implementation = ResourceDTO.class)))
    public ResponseEntity<ResourceDTO> getResourceById(
            @Parameter(description = "ID of the resource to retrieve") @PathVariable Long resourceId) {
        ResourceDTO resource = resourceService.getResourceById(resourceId);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping("/{resourceId}/availability")
    @Operation(summary = "Check resource availability", description = "Checks if a resource is available")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved resource availability", content = @Content(schema = @Schema(implementation = Boolean.class)))
    public ResponseEntity<Boolean> isResourceAvailable(
            @Parameter(description = "ID of the resource to check availability") @PathVariable Long resourceId) {
        boolean isAvailable = resourceService.isResourceAvailable(resourceId);
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }

    @GetMapping("/{resourceId}/over-allocated/{projectId}")
    @Operation(summary = "Check resource over-allocation", description = "Checks if a resource is over-allocated for a specific project")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved resource over-allocation status", content = @Content(schema = @Schema(implementation = Boolean.class)))
    public ResponseEntity<Boolean> isResourceOverAllocated(
            @Parameter(description = "ID of the resource to check") @PathVariable Long resourceId,
            @Parameter(description = "ID of the project to check against") @PathVariable Long projectId) {
        boolean isOverAllocated = resourceService.isResourceOverAllocated(resourceId, projectId);
        return new ResponseEntity<>(isOverAllocated, HttpStatus.OK);
    }
}

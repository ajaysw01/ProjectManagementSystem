package com.ajaysw.ProjectManagementSystem.service;


import com.ajaysw.ProjectManagementSystem.entity.Resource;
import com.ajaysw.ProjectManagementSystem.entity.ResourceDTO;
import com.ajaysw.ProjectManagementSystem.enums.ResourceStatus;
import com.ajaysw.ProjectManagementSystem.exception.ResourceNotFoundException;
import com.ajaysw.ProjectManagementSystem.respository.ResourceRepository;
import com.ajaysw.ProjectManagementSystem.respository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ajay Wankhade
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public ResourceDTO createResource(ResourceDTO resourceDTO) { // Change parameter type
        Resource resource = new Resource(); // Create new Resource entity
        resource.setName(resourceDTO.getName());
        resource.setStatus(resourceDTO.getStatus());

        return convertToDTO(resourceRepository.save(resource)); // Convert to DTO before returning
    }

    @Override
    public ResourceDTO updateResource(Long resourceId, ResourceDTO resourceDTO) {
        Resource existingResource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id " + resourceId));

        existingResource.setName(resourceDTO.getName());
        existingResource.setStatus(resourceDTO.getStatus());

        return convertToDTO(resourceRepository.save(existingResource)); // Convert to DTO before returning
    }

    @Override
    public void deleteResource(Long resourceId) {
        resourceRepository.deleteById(resourceId);
    }

    @Override
    public List<ResourceDTO> getAllResources() {
        return resourceRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Resource getResourceEntityById(Long resourceId) {
        return resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id " + resourceId));
    }


    @Override
    public ResourceDTO getResourceById(Long resourceId) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id " + resourceId));
        return convertToDTO(resource);
    }

    @Override
    public boolean isResourceOverAllocated(Long resourceId, Long projectId) {
        long taskCount = taskRepository.countByResourceIdAndProjectId(resourceId, projectId);
        return taskCount >= 2;
    }

    @Override
    public boolean isResourceAvailable(Long resourceId) {
        ResourceDTO resourceDTO = getResourceById(resourceId);
        return resourceDTO.getStatus().equals(ResourceStatus.AVAILABLE);
    }

    private ResourceDTO convertToDTO(Resource resource) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(resource.getId());
        resourceDTO.setName(resource.getName());
        resourceDTO.setStatus(resource.getStatus());
        return resourceDTO;
    }

}

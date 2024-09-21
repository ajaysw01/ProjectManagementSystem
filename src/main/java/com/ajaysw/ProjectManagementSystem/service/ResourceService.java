package com.ajaysw.ProjectManagementSystem.service;

import com.ajaysw.ProjectManagementSystem.entity.Resource;
import com.ajaysw.ProjectManagementSystem.entity.ResourceDTO;

import java.util.List;

/**
 * @author Ajay Wankhade
 */
public interface ResourceService {


    ResourceDTO createResource(ResourceDTO resourceDTO);

    ResourceDTO updateResource(Long resourceId, ResourceDTO resourceDTO);

    void deleteResource(Long resourceId);

    List<ResourceDTO> getAllResources();

    Resource getResourceEntityById(Long resourceId);

    ResourceDTO getResourceById(Long resourceId);

    boolean isResourceOverAllocated(Long resourceId, Long projectId);

    boolean isResourceAvailable(Long resourceId);
}

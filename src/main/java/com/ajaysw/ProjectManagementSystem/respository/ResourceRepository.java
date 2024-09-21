package com.ajaysw.ProjectManagementSystem.respository;

import com.ajaysw.ProjectManagementSystem.entity.Resource;
import com.ajaysw.ProjectManagementSystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Ajay Wankhade
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {}





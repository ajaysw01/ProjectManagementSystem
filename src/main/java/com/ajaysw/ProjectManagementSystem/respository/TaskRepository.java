package com.ajaysw.ProjectManagementSystem.respository;

import com.ajaysw.ProjectManagementSystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Ajay Wankhade
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);

    long countByResourceIdAndProjectId(Long projectId, Long resourceId);

}
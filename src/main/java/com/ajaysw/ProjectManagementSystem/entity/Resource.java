
package com.ajaysw.ProjectManagementSystem.entity;

import com.ajaysw.ProjectManagementSystem.enums.ResourceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ajay Wankhade
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ResourceStatus status;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();
}

## Project Management API 

This README file provides an overview of the Project Management API. 

###  API Description

This API allows you to manage projects, resources, and tasks. It uses the OpenAPI Specification (OAS3) to define the available endpoints and their functionalities.

###  API Information

* **Title:** Project Management API
* **Description:** API for managing Project Management
* **Contact:** Ajay Wankhade (ajaysw45@gmail.com)
* **Version:** 1.0

###  Server Information

The API runs on a local server at `http://localhost:8080`.

###  API Endpoints

The API provides various endpoints for managing projects, resources, and tasks. Here's a summary of the endpoints categorized by functionality:

**Project Management:**

* **Get all projects:** `/api/projects (GET)`
* **Create a new project:** `/api/projects (POST)` (Requires project details in request body)
* **Get a project by ID:** `/api/projects/{projectId} (GET)` (Replace `{projectId}` with the actual project ID)
* **Update an existing project:** `/api/projects/{projectId} (PUT)` (Replace `{projectId}` with the actual project ID. Requires updated project details in request body)
* **Delete a project:** `/api/projects/{projectId} (DELETE)` (Replace `{projectId}` with the actual project ID)
* **Get tasks for a project:** `/api/projects/{projectId}/tasks (GET)` (Replace `{projectId}` with the actual project ID)

**Resource Management:**

* **Get all resources:** `/api/resources (GET)`
* **Create a new resource:** `/api/resources (POST)` (Requires resource details in request body)
* **Get a resource by ID:** `/api/resources/{resourceId} (GET)` (Replace `{resourceId}` with the actual resource ID)
* **Update an existing resource:** `/api/resources/{resourceId} (PUT)` (Replace `{resourceId}` with the actual resource ID. Requires updated resource details in request body)
* **Delete a resource:** `/api/resources/{resourceId} (DELETE)` (Replace `{resourceId}` with the actual resource ID)
* **Check resource availability:** `/api/resources/{resourceId}/availability (GET)` (Replace `{resourceId}` with the actual resource ID)
* **Check resource over-allocation for a project:** `/api/resources/{resourceId}/over-allocated/{projectId} (GET)` (Replace `{resourceId}` with the actual resource ID and `{projectId}` with the project ID to check against)

**Task Management:**

* **Get a task by ID:** `/api/tasks/{taskId} (GET)` (Replace `{taskId}` with the actual task ID)
* **Update an existing task:** `/api/tasks/{taskId} (PUT)` (Replace `{taskId}` with the actual task ID. Requires updated task details in request body)
* **Delete a task:** `/api/tasks/{taskId} (DELETE)` (Replace `{taskId}` with the actual task ID)
* **Create a new task for a project:** `/api/tasks/{projectId} (POST)` (Replace `{projectId}` with the actual project ID. Requires task details in request body)
* **Allocate a resource to a task:** `/api/tasks/{taskId}/allocate-resource/{resourceId} (POST)` (Replace `{taskId}` with the actual task ID and `{resourceId}` with the resource ID to allocate)

###  Data Models (DTO)

The API uses Data Transfer Objects (DTOs) to represent project, resource, and task data. Here's a breakdown of the available DTOs:

* **TaskDTO:**
    * Properties:
        * id (integer): Unique identifier for the task
        * name (string): Name of the task
        * status (string): Status of the task (PENDING, IN_PROGRESS, COMPLETED)
* **ResourceDTO:**
    * Properties:
        * id (integer): Unique identifier for the resource
        * name (string): Name of the resource
        * status (string): Status of the resource (AVAILABLE, UNAVAILABLE)
* **ProjectDTO:**
    * Properties:
        * id (integer): Unique identifier for the project
        * name (string): Name of the project
        * description (string): Description of the project
        * tasks (array of TaskDTO): List of tasks associated with the project

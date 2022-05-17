A sample REST API for managing tasks using Spring Boot

Spring Boot : For creating a stand alone application
Database : H2 database, where data is persisted in a file
Language : Java

The application provides a REST api for managing taks. Below are the currently supported operations

Add a task
Find a task by id
Find all available tasks
Modify an existing task
Delete a task
Delete an existing task

HTTP Method      URI            Description of the operation
GET             api/tasks       Retrieves the list of all tasks
GET             api/tasks/{id}  gets the task if the id matches
PUT             api/tasks/{id}  updates a task for the given id
DELETE          api/tasks/{id}  delete the task for the given id
DELETE          api/tasks/      deletes all the tasks


A sample REST API for managing tasks using Spring Boot

Spring Boot : For creating a stand alone application
Database : H2 database, where data is persisted in a file
Language : Java

Instructions :
Once the application is started you can see if the database tables are created by checking via
the url : http://localhost:8080/h2-console (or the corresponding port you would use locally)
By default the the property -> spring.sql.init.mode=always is configured so that anyone who downloads this
code will have the db table created as well as the data will get inserted.
If the application is run once, you disable this property -> spring.sql.init.mode=always
and enable the property spring.jpa.hibernate.ddl-auto=validate
Any data modified will be persisted if the application is started on validate mode.
The table and its data is not overwritten when application is started in validate mode.

The application provides a REST api for managing taks. Below are the currently supported operations

Add a task
Find a task by id
Find all available tasks
Modify an existing task
Delete a task
Delete an existing task

HTTP Method      URI                                     Description of the operation
GET             api/tasks                                Retrieves the list of all tasks
GET             api/tasks/{id}                           Gets the task if the id matches
PUT             api/tasks/{id}                           Updates a task for the given id
DELETE          api/tasks/{id}                           Delete the task for the given id
DELETE          api/tasks/                               Deletes all the tasks


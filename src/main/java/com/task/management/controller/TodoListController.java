package com.task.management.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.management.entity.Task;
import com.task.management.errors.TaskNotFoundException;
import com.task.management.service.TaskService;

@RestController
public class TodoListController {

  @Autowired
  private TaskService taskService;


  @PostMapping("/api/tasks")
  public Task addTask(@Valid @NotNull Task task) {
    return taskService.addTask(task);
  }

  @GetMapping("/api/tasks")
  public List<Task> getAllTask() {
    return taskService.getAllTasks();
  }

  @GetMapping("/api/tasks/{id}")
  public Optional<Task> getTaskById(@PathVariable("id") Long id) {
    return taskService.getTaskById(id);
  }

  @PutMapping("/api/tasks/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @Valid @NotNull @RequestBody Task updatedTask) {

    return taskService.getTaskById(id)
        .map(x -> {
          x.setCreateDate(new Date());
          x.setDescription(updatedTask.getDescription());
          x.setName(updatedTask.getName());
          x.setCompleted(updatedTask.getCompleted());
          Task newUpdateTask = taskService.updateTask(x);
          return new ResponseEntity<>(newUpdateTask, HttpStatus.OK);
        }).orElseThrow(() -> new TaskNotFoundException(id));
  }

  @DeleteMapping("/api/tasks/{id}")
  public ResponseEntity<String> deleteTaskById(@PathVariable("id") Long id) {
    taskService.deleteTask(id);
    return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
  }

  @DeleteMapping("/api/tasks")
  public void deleteAllTasks() {
    taskService.deleteAllTasks();
  }
}

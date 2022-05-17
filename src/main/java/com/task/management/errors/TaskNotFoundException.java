package com.task.management.errors;

public class TaskNotFoundException extends RuntimeException {

  public TaskNotFoundException(Long id) {
    super("Task id not found" + id);
  }
}

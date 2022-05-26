package com.task.management.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends Exception {

  public TaskNotFoundException(int id) {
    super("Task id not found " + id);
  }
}

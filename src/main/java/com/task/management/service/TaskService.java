package com.task.management.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.task.management.entity.Task;


public interface TaskService {

  List<Task> getAllTasks();

  Optional<Task> getTaskById(Long id);

  Task addTask(@NotNull Task task);

  Task updateTask(@NotNull Task task);

  void deleteTask(Long id);

  void deleteAllTasks();

}

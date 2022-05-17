package com.task.management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.management.entity.Task;
import com.task.management.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

  @Autowired
  private TaskRepository taskRepository;

  public List<Task> getAllTasks() {
    List<Task> tasks = new ArrayList<>();
    taskRepository.findAll().forEach(task -> tasks.add(task));
    return tasks;
  }

  public Optional<Task> getTaskById(Long id) {
    return taskRepository.findById(id);
  }

  public Task addTask(@NotNull Task task) {
    task.setCompleted("pending");
    return taskRepository.save(task);
  }

  public Task updateTask(@NotNull Task task) {
    return taskRepository.save(task);
  }

  public void deleteTask(Long id) {
    taskRepository.deleteById(id);
  }

  public void deleteAllTasks() {
    taskRepository.deleteAll();
  }
}

package com.task.management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.management.entity.Task;
import com.task.management.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

  @Autowired
  private TaskRepository taskRepository;

  @Override
  public List<Task> getAllTasks() {
    return StreamSupport.stream(taskRepository.findAll().spliterator(), false).collect(Collectors.toList());
  }

  @Override
  public Optional<Task> getTaskById(Long id) {
    return taskRepository.findById(id);
  }

  @Override
  public Task addTask(@NotNull Task task) {
    task.setCompleted("pending");
    return taskRepository.save(task);
  }

  @Override
  public Task updateTask(@NotNull Task task) {
    return taskRepository.save(task);
  }

  @Override
  public void deleteTask(Long id) {
    taskRepository.deleteById(id);
  }

  @Override
  public void deleteAllTasks() {
    taskRepository.deleteAll();
  }
}

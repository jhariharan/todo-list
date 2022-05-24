package com.task.management.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.task.management.entity.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>, QueryByExampleExecutor<Task> {
}

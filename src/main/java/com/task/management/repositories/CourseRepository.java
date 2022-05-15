package com.task.management.repositories;

import org.springframework.data.repository.CrudRepository;

import com.task.management.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}

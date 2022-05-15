package com.task.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.management.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}

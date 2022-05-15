package com.task.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.management.entity.Course;
import com.task.management.errors.CourseNotFoundException;
import com.task.management.repositories.CourseRepository;

@RestController
public class TodoListController {

  @Autowired
  private CourseRepository courseRepository;


  @GetMapping("/courses")
  private List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  @GetMapping("/course/{id}")
  private Course getCourseById(@PathVariable("id") int id) {
    return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
  }

  @PostMapping("/course")
  private Course addCourse(@RequestBody Course newCourse) {
    return courseRepository.save(newCourse);
  }

  @DeleteMapping("/course/{id}")
  private void deleteCourse(@PathVariable("id") int id) {
    courseRepository.deleteById(id);
  }
}

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
import com.task.management.service.CourseService;

@RestController
public class TodoListController {

  @Autowired
  private CourseService courseService;


  @GetMapping("/course")
  private List<Course> getAllCourses() {
    return courseService.getAllCourses();
  }

  @GetMapping("/course/{id}")
  private Course getCourseById(@PathVariable("id") int id) {
    return courseService.getCoursById(id);
  }

  @PostMapping("/course")
  private int addOrUpdateCourse(@RequestBody Course course) {
    courseService.saveOrUpdateCourse(course);
    return course.getId();
  }

  @DeleteMapping("/course/{id}")
  private void deleteCourse(@PathVariable("id") int id){
    courseService.deleteCourse(id);
  }
}

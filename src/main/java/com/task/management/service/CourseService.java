package com.task.management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.management.entity.Course;
import com.task.management.repositories.CourseRepository;

@Service
public class CourseService {

  @Autowired
  private CourseRepository courseRepository;

  public List<Course> getAllCourses(){
    List<Course> courses = new ArrayList<>();
     courseRepository.findAll().forEach(course -> courses.add(course));
     return courses;
  }

  public Course getCoursById(int id){
    return courseRepository.findById(id).get();
  }

  public void saveOrUpdateCourse(Course course){
  courseRepository.save(course);
  }

  public void deleteCourse(int id){
  courseRepository.deleteById(id);
  }
}

package com.task.management.errors;

public class CourseNotFoundException extends RuntimeException {

  public CourseNotFoundException(int id){
    super("Book id not found" +id);
  }
}

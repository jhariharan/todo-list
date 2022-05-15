package com.task.management;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.task.management.controller.TodoListController;
import com.task.management.repositories.CourseRepository;

@RunWith(MockitoJUnitRunner.class)
class TodoListApplicationTests {

  @InjectMocks
  TodoListController todoListController;

  @Mock
  CourseRepository courseRepository;

  @Test
  void contextLoads() {
    //
  }

}

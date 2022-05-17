package com.task.management.Controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.management.entity.Task;
import com.task.management.service.TaskService;

@WebMvcTest
public class TodoListControllerTest {

  private static final Long ID = 1L;
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  private TaskService taskService;

  @Test
  public void given_task_id_when_valid_task_id_then_return_task_object() throws Exception {
    Task task = getByTaskId();
    given(taskService.getTaskById(ID)).willReturn(Optional.of(task));
    ResultActions response = mockMvc.perform(get("/api/tasks/{id}", ID));

    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.description").exists());
  }

  @Test
  public void given_task_id_when_invalid_task_id_then_return_nothing() throws Exception {
    given(taskService.getTaskById(2L)).willReturn(Optional.empty());
    ResultActions response = mockMvc.perform(get("/api/tasks/{id}", ID));

    response.andExpect(status().isNotFound())
        .andDo(print());
  }

  private Task getByTaskId() {
    Task task = new Task();
    task.setId(1L);
    task.setName("name");
    task.setDescription("new task");
    task.setCompleted("pending");
    task.setCreateDate(new Date());
    return task;
  }
}

package com.task.management.Controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.management.entity.Task;
import com.task.management.service.TaskService;
import net.minidev.json.JSONObject;

@WebMvcTest
@AutoConfigureMockMvc
public class TodoListControllerTest {

  private static final int ID = 1;
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @MockBean
  private TaskService taskService;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void shouldReturnATaskGivenAnId() throws Exception {
    Task task = getByTaskId();
    given(taskService.getTaskById(ID)).willReturn(Optional.of(task));
    mockMvc.perform(get("/api/tasks/{id}", 1)).andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  public void shouldGiveNotFoundIfIdDoesNotExist() throws Exception {
    given(taskService.getTaskById(Integer.MAX_VALUE)).willReturn(Optional.empty());

    mockMvc.perform(get("/api/tasks/{id}", Integer.MAX_VALUE)).andExpect(status().isNotFound())
        .andDo(print());
  }

  @Test
  public void shouldFindAllTasks() throws Exception {
    {
      mockMvc.perform(MockMvcRequestBuilders
          .get("/api/tasks")
          .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
          .andDo(print());
    }
  }

  @Test
  public void shouldDeleteTaskById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .delete("/api/tasks", 1)
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  public void shouldAddATask() throws Exception {
    final String requestBody = createValidJsonRequestBody().toJSONString();

    mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks")
        .accept(MediaType.APPLICATION_JSON)
        .content(requestBody)
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk());
  }


  private Task getByTaskId() {
    Task task = new Task();
    task.setId(1);
    task.setName("name");
    task.setDescription("new task");
    task.setCompleted("pending");
    task.setCreateDate(new Date());
    return task;
  }

  private JSONObject createValidJsonRequestBody() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", "updatedName");
    jsonObject.put("description", "updatedDescription");
    jsonObject.put("id", 1);
    return jsonObject;
  }
}

package com.task.management.Controllers;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.task.management.TodoListApplication;
import com.task.management.entity.Task;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoListApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoListControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  private String getRootUrl() {
    return "http://localhost:" + port;
  }

  @Test
  public void contextLoads() {

  }

  @Test
  public void testGetAllTasks() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/tasks",
        HttpMethod.GET, entity, String.class);
    assertNotNull(response.getBody());
  }

  @Test
  public void testGetaTaskId() {
    Task task = restTemplate.getForObject(getRootUrl() + "/api/tasks/1", Task.class);
    Assert.assertNotNull(task);
  }

  @Test
  public void testCreateTask() {
    Task task = new Task();
    task.setId(1);
    task.setName("name");
    task.setDescription("new task");
    task.setCompleted("pending");
    task.setCreateDate(new Date());

    ResponseEntity<Task> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/tasks", task, Task.class);
    Assert.assertNotNull(postResponse);
    Assert.assertNotNull(postResponse.getBody());
  }

  @Test
  public void testUpdateTask() {
    int id = 1;
    Task task = restTemplate.getForObject(getRootUrl() + "/api/tasks" + id, Task.class);
    task.setDescription("completed");

    restTemplate.put(getRootUrl() + "/api/tasks" + id, task);

    Task updatedTask = restTemplate.getForObject(getRootUrl() + "/api/tasks" + id, Task.class);
    Assert.assertNotNull(updatedTask);
  }

  @Test
  public void testDeleteTask() {
    int id = 2;
    Task task = restTemplate.getForObject(getRootUrl() + "/api/tasks" + id, Task.class);
    Assert.assertNotNull(task);

    restTemplate.delete(getRootUrl() + "/api/tasks" + id);

    try {
      task = restTemplate.getForObject(getRootUrl() + "/api/tasks" + id, Task.class);
    } catch (final HttpClientErrorException e) {
      assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
    }
  }

}

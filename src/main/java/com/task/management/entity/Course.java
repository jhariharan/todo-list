package com.task.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Course {

  @Id
  private int id;
  @Column
  private String name;
  @Column
  private String description;
  @Column
  private String status;

  public Course() {

  }

  public Course(int id, String name, String status, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(final String status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }
}

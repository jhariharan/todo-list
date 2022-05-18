package com.task.management.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table
public class Task {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @Column
  private String name;

  @Column
  private String description;

  @Column
  private String completed;

  @Temporal(TemporalType.DATE)
  private Date createdate;

  public Task() {
//
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public String getCompleted() {
    return completed;
  }

  public void setCompleted(final String completed) {
    this.completed = completed;
  }

  public Date getCreateDate() {
    return createdate;
  }

  public void setCreateDate(final Date createDate) {
    this.createdate = createDate;
  }

  @PrePersist
  void onCreate() {
    this.setCreateDate(new Date());
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Task task = (Task) o;

    if (id != null ? !id.equals(task.id) : task.id != null) {
      return false;
    }
    if (description != null ? !description.equals(task.description) : task.description != null) {
      return false;
    }
    return completed != null ? completed.equals(task.completed) : task.completed == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (completed != null ? completed.hashCode() : 0);
    return result;
  }
}


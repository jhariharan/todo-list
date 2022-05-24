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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


@Entity
@Table
public class Task {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
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

    if (!(o instanceof Task)) {
      return false;
    }

    final Task that = (Task) o;

    return new EqualsBuilder()
        .append(getName(), that.getName())
        .append(getCompleted(), that.getCompleted())
        .append(getDescription(), that.getDescription())
        .isEquals();
  }

  @Override
  public int hashCode() {
    int result = Integer.valueOf(id).hashCode();
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (completed != null ? completed.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}


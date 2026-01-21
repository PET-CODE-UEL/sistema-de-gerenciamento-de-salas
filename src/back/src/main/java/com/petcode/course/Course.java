package com.petcode.course;

import com.petcode.department.Department;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Course {

  @Id
  @Column(name = "code")
  private String code;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @ManyToOne
  @JoinColumn(name = "department_id", nullable = false)
  private Department department;

  protected Course() {
  }

  public Course(String code, String name, Department department) {
    this.code = code;
    this.name = name;
    this.department = department;
  }

  @Override
  public String toString() {
    return String.format("Course[%s : %s]", this.code, this.name);
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

}

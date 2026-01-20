package com.petcode.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

  @Id
  @Column(name = "code")
  private String code;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @ManyToOne
  @JoinColumn(name = "center_id")
  private Center center;

  protected Department() {
  }

  public Department(String code, String name, Center center) {
    this.code = code;
    this.name = name;
    this.center = center;
  }

  @Override
  public String toString() {
    return String.format("Department[%s : %s]", this.code, this.name);
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

  public Center getCenter() {
    return center;
  }

  public void setCenter(Center center) {
    this.center = center;
  }

}

package com.petcode.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "centers")
public class Center {

  @Id
  @Column(name="code")
  private String code;

  @Column(name="name", nullable = false, length = 100)
  private String name;

  protected Center() {}

  public Center(String code, String name) {
    this.code = code;
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("Center[%s : %s]", this.code, this.name);
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

}

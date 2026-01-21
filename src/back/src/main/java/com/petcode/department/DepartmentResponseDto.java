package com.petcode.department;

import com.petcode.department.Department;

public class DepartmentResponseDto {
  private String code;
  private String name;
  private String centerCode;

  public DepartmentResponseDto(Department d) {
    this.name = d.getName();
    this.code = d.getCode();
    if (d.getCenter() != null) {
      this.centerCode = d.getCenter().getCode();
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCenterCode() {
    return centerCode;
  }

  public void setCenterCode(String centerCode) {
    this.centerCode = centerCode;
  }
}
